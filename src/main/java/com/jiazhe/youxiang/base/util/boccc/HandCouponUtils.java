package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TU
 * @description 优惠券信息工具类
 * @date 2019-09-03.
 */
public class HandCouponUtils {

    public static Logger logger = LoggerFactory.getLogger(HandCouponUtils.class);

    /**
     * 11位
     * 商品的唯一编码，命名规则为WXXXXNNNNNN
     * (XXXX为四位第三方系统名称，NNNNNN为数字编码，范围为000001~999999)
     */
    private final static String[] ProductIds = {"WXXXX0000001"};

    /**
     * 第三方系统中代金券批次id
     */
    private final static String[] CouponBatchIds = {"1"};

    /**
     * 检查各个参数是否合法
     *
     * @throws Exception
     */
    public static void check() throws Exception {
        int count = ProductIds.length;
        if (CouponBatchIds.length != count) {
            throw new Exception("CouponBatchIds长度和ProductIds不匹配");
        }
    }

    public static StringBuilder generateBin() throws Exception {
        List<CouponEntity> list = getList();
        StringBuilder sb = new StringBuilder();
        for (CouponEntity coupon : list) {
            sb.append(coupon.getProductId()).append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete(coupon.getId(), '0', true, 10)).append(BOCCCConstant.BOC_Separator);
            sb.append("E").append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete(coupon.getKeyt(), '0', true, 36)).append(BOCCCConstant.BOC_Separator);
            //预留字段还未拼接 TODO
            //TODO
            //换行
            sb.append("\r\n");
        }
        //添加文件尾部信息
        sb.append(BOCCCUtils.generateFileEndChar(list.size()));
        return sb;
    }

    public static List<CouponEntity> getList() throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String, String> map = new HashMap();
        for (int i = 0; i < CouponBatchIds.length; i++) {
            map.put(CouponBatchIds[i], ProductIds[i]);
            sql.append("select * from voucher_exchange_code where batch_id ='" + CouponBatchIds[i] + "' ");
            if (i != CouponBatchIds.length - 1) {
                sql.append("union ALL ");
            }
        }
        List<CouponEntity> list = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        //本地环境数据库
        String url = "jdbc:mysql://localhost:3306/youxiang?useUnicode=true&characterEncoding=UTF8&connectTimeout=1000&socketTimeout=10000&allowMultiQueries=true";
        Connection conn = DriverManager.getConnection(url, "root", "root");
//        //测试环境数据库
//        String url = "jdbc:mysql://cdb-21q33fb6.bj.tencentcdb.com:10018/youxiang?useUnicode=true&characterEncoding=UTF8&connectTimeout=1000&socketTimeout=10000&allowMultiQueries=true";
//        Connection conn = DriverManager.getConnection(url, "root", "rewq4321$#@!");
//        //生产环境数据库
//        String url = "jdbc:mysql://bj-cdb-9l8ozcar.sql.tencentcdb.com:63546/youxiang?characterEncoding=UTF-8&useCursorFetch=true&defaultFetchSize=2000";
//        Connection conn = DriverManager.getConnection(url, "root", "rewq4321++");
        int count = 0, error = 0;
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery(sql.toString());
        while (rs.next()) {
            CouponEntity coupon = new CouponEntity();
            coupon.setId(rs.getString("id"));
            coupon.setBatchId(rs.getString("batch_id"));
            coupon.setKeyt(rs.getString("keyt"));
            String productId = rs.getString("boccc_product_id");
            coupon.setProductId(productId);
            if (!productId.equals(map.get(coupon.getBatchId()))) {
                error++;
            }
            count++;
            list.add(coupon);
        }
        conn.close();//关闭通道
        if (error > 0) {
            logger.error("BOCCC-ERROR：生成优惠券总数：" + count + "，错误个数：" + error + "个，原因，数据库中boccc_product_id和本类中ProductIds对不上！");
        } else {
            logger.info("成功生成优惠券：" + count + "个。");
        }
        return list;
    }


    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径 day=-1表示昨日文件  0表示今日文件   1表示明日文件
        int day = 1;
        String sourceFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.COUPON_SOURCE, day);
        String zipFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.COUPON_ZIP, day);
        String pgpFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.COUPON_PGP, day);


        //第一步，检查各个参数是否合法
        check();

        //第二步，按照规则组成商品信息字符串
        StringBuilder sb = generateBin();

        //第三步，写入文件中
        logger.info("优惠券信息源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("优惠券信息源文件生成完成，路径为：" + sourceFileName);

        //第四步，源文件压缩中
        logger.info("优惠券信息源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("优惠券信息源文件压缩完成，路径为：" + zipFileName);

        //第五步，压缩文件加密中
        logger.info("优惠券信息压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("优惠券信息压缩文件加密完成，路径为：" + pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }
}
