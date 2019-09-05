package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author TU
 * @description 商户信息工具类
 * @date 2019-09-03.
 */
public class MerchantInfoUtils {

    public static Logger logger = LoggerFactory.getLogger(MerchantInfoUtils.class);

    /**
     * 省
     */
    private final static String Province = "110000";

    /**
     * 市
     */
    private final static String City = "110100";

    /**
     * 县
     */
    private final static String County = "110114";

    /**
     * 商户名称
     */
    private final static String MerchantName = "北京悠享互联信息技术有限公司";

    /**
     * 门店名称
     */
    private final static String ShopName = "北京悠享";

    /**
     * 商户简介
     */
    private final static String MerchantDescription = "家政先锋";

    /**
     * 门店地址
     */
    private final static String ShopAddress = "北京市昌平区珠江摩尔";

    /**
     * 门店电话
     */
    private final static String ShopTel = "010-88888888";

    /**
     * 门店营业时间 hh24:mm:ss  -  hh24:mm:ss
     */
    private final static String ShopWorkTime = "hh24:mm:ss-hh24:mm:ss";

    /**
     * 商户网址
     */
    private final static String MerchantWebSite = "www.ue-link.com";

    /**
     * 经纬度
     */
    private final static String Longitude = "116.24";
    private final static String Latitude = "39.56";

    /**
     * 门店图片ID
     */
    public final static String PicId = "1";

    public static String getPicId() throws Exception {
        return "P" + BOCCCConstant.MERCHANT_NAME + BOCCCUtils.complete(PicId, '0', true, 5);
    }

    /**
     * 门店是否开启  门店是否关闭标识，Y是正常、N是关闭
     */
    private final static String isOpen = "Y";

    /**
     * 检查各个参数是否合法
     *
     * @throws Exception
     */
    public static void check() throws Exception {

    }

    public static StringBuilder generateBin() throws Exception {
        StringBuilder sb = new StringBuilder();
        //添加商户id
        sb.append(BOCCCConstant.MERCHANT_ID).append(BOCCCConstant.BOC_Separator);
        //添加省，市，县
        sb.append(Province).append(BOCCCConstant.BOC_Separator);
        sb.append(City).append(BOCCCConstant.BOC_Separator);
        sb.append(County).append(BOCCCConstant.BOC_Separator);
        //添加商户名称
        sb.append(BOCCCUtils.complete(MerchantName, ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
        //添加门店名称
        sb.append(BOCCCUtils.complete(ShopName, ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
        //添加商户简介
        sb.append(BOCCCUtils.complete(MerchantDescription, ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
        //添加门店地址
        sb.append(BOCCCUtils.complete(ShopAddress, ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
        //添加门店电话
        sb.append(BOCCCUtils.complete(ShopTel, ' ', false, 20)).append(BOCCCConstant.BOC_Separator);
        //添加门店营业时间
        sb.append(BOCCCUtils.complete(ShopWorkTime, ' ', false, 100)).append(BOCCCConstant.BOC_Separator);
        //添加网站
        sb.append(BOCCCUtils.complete(MerchantWebSite, ' ', false, 100)).append(BOCCCConstant.BOC_Separator);
        //添加经纬度
        sb.append(BOCCCUtils.complete(Longitude, ' ', true, 50)).append(BOCCCConstant.BOC_Separator);
        sb.append(BOCCCUtils.complete(Latitude, ' ', true, 50)).append(BOCCCConstant.BOC_Separator);
        //添加图片ID
        sb.append(getPicId()).append(BOCCCConstant.BOC_Separator);
        //门店开启关闭状态
        sb.append(isOpen).append(BOCCCConstant.BOC_Separator);
        //预留字段还未拼接 TODO
        //TODO
        //换行
        sb.append("\r\n");
        //添加文件尾部信息
        sb.append(BOCCCUtils.generateFileEndChar(1));
        return sb;
    }

    /**
     * 源文件的文件路径
     *
     * @return
     */
    public static String generateSourceFileName() {
        return "MER." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.P";
    }

    /**
     * 压缩文件的文件路径
     *
     * @return
     */
    public static String generateZipFileName() {
        return "MER." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.P.ZIP";
    }

    /**
     * 加密文件文件路径
     *
     * @return
     */
    public static String generatePgpFileName() {
        return "MER." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.getToday() + ".00.P.ZIP.DAT";
    }


    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = BOCCCConstant.rootPath + generateSourceFileName();
        String zipFileName = BOCCCConstant.rootPath + generateZipFileName();
        String pgpFileName = BOCCCConstant.rootPath + generatePgpFileName();

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
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("压缩文件加密完成，路径为：" + pgpFileName);

//        //第六步，文件解密中
//        PgpDecryUtil decryU = new PgpDecryUtil();
//        decryU.setPassphrase("youxianghulian0612");
//        decryU.DecryUtil(pgpFileName, zipFileName, BOCCCConstant.privateKeyPath);
//
//        //第七步，文件解压缩
//        UnZipUtil.ZipContraFile(zipFileName, BOCCCConstant.rootPath);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }
}
