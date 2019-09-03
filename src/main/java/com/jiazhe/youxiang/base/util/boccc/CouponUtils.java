package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TU
 * @description 优惠券信息工具类
 * @date 2019-09-03.
 */
public class CouponUtils {

    public static Logger logger = LoggerFactory.getLogger(CouponUtils.class);

    /**
     * 11位
     * 商品的唯一编码，命名规则为WXXXXNNNNNN
     * (XXXX为四位第三方系统名称，NNNNNN为数字编码，范围为000001~999999)
     */
    private final static String[] ProductIds = {"123","456"};

    public static String getProductId(String id) throws Exception {
        return "W" + BOCCCConstant.MERCHANT_NAME + BOCCCUtils.complete(id, '0', true, 6);
    }

    /**
     * 第三方系统中代金券批次id
     */
    private final static String[] CouponBatchIds = {"1","2"};

    /**
     * 检查各个参数是否合法
     *
     * @throws Exception
     */
    public static void check() throws Exception {
        int count = ProductIds.length;
        if (CouponBatchIds.length != count) {
            throw new Exception("ProductNames长度和ProductIds不匹配");
        }
    }

    public static StringBuilder generateBin() throws Exception {
        List<CouponEntity> list = getList();
        StringBuilder sb = new StringBuilder();
        for (CouponEntity coupon : list) {
            sb.append(getProductId(coupon.getProductId())).append(BOCCCConstant.BOC_Separator);
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

    /**
     * 源文件的文件路径
     *
     * @return
     */
    public static String generateSourceFileName() {
        return BOCCCConstant.rootPath + "COUPON." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.P";
    }

    /**
     * 压缩文件的文件路径
     *
     * @return
     */
    public static String generateZipFileName() {
        return BOCCCConstant.rootPath + "COUPON." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.P.ZIP";
    }

    /**
     * 加密文件文件路径
     *
     * @return
     */
    public static String generatePgpFileName() {
        return BOCCCConstant.rootPath + "COUPON." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.P.ZIP.DAT";
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
        //测试环境数据库
        String url = "jdbc:mysql://cdb-21q33fb6.bj.tencentcdb.com:10018/youxiang?useUnicode=true&characterEncoding=UTF8&connectTimeout=1000&socketTimeout=10000&allowMultiQueries=true";
        Connection conn = DriverManager.getConnection(url, "root", "rewq4321$#@!");
//        String url = "jdbc:mysql://bj-cdb-9l8ozcar.sql.tencentcdb.com:63546/coupon?characterEncoding=UTF-8&useCursorFetch=true&defaultFetchSize=2000";
//        Connection conn = DriverManager.getConnection(url, "root", "rewq4321++");
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery(sql.toString());
        while (rs.next()) {
            CouponEntity coupon = new CouponEntity();
            coupon.setId(rs.getString("id"));
            coupon.setBatchId(rs.getString("batch_id"));
            coupon.setKeyt(rs.getString("keyt"));
            coupon.setProductId(map.get(coupon.getBatchId()));
            list.add(coupon);
        }
        conn.close();//关闭通道
        return list;
    }


    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = generateSourceFileName();
        String zipFileName = generateZipFileName();
        String pgpFileName = generatePgpFileName();

        //第一步，检查各个参数是否合法
        check();

        //第二步，按照规则组成商品信息字符串
        StringBuilder sb = generateBin();

        //第三步，写入文件中
        logger.info("源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("源文件生成完成，路径为：" + sourceFileName);

        //第四步，源文件压缩中
        logger.info("源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("源文件压缩完成，路径为：" + zipFileName);

        //第五步，压缩文件加密中
        logger.info("压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, generatePgpFileName());
        logger.info("压缩文件加密完成，路径为：" + pgpFileName);

//        //第六步，文件解密中
//        logger.info("加密文件解密中...");
//        PgpDecryUtil decryU = new PgpDecryUtil();
//        decryU.setPassphrase("youxianghulian0612");
//        decryU.DecryUtil(pgpFileName, zipFileName, BOCCCConstant.privateKeyPath);
//        logger.info("解密完成，路径为：" + zipFileName);
//
//        //第七步，文件解压缩
//        logger.info("文件解压中...");
//        UnZipUtil.ZipContraFile(zipFileName, sourceFileName.substring(0,sourceFileName.lastIndexOf("\\")));
//        System.out.println("解压后路径" + sourceFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }
}
