package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.common.constant.BOCCCConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

/**
 * @author TU
 * @description 中行工具类
 * @date 2019-09-02.
 */
public class ProductInfoUtils {

    public static Logger logger = LoggerFactory.getLogger(ProductInfoUtils.class);

    /**
     * 11位
     * 商品的唯一编码，命名规则为WXXXXNNNNNN
     * (XXXX为四位第三方系统名称，NNNNNN为数字编码，范围为000001~999999)
     */
    private final static String[] ProductIds = {"1"};

    public static String getProductId(String id) throws Exception {
        return "W" + BOCCCConstant.MERCHANT_NAME + BOCCCUtils.complete(id, '0', true, 6);
    }

    /**
     * 40位   需要补全
     * 商品中文名称
     */
    private final static String[] ProductNames = {"擦玻璃"};

    /**
     * 2位  不用补全
     * 商品所属大类
     * 00	酒店会所
     * 01	饕餮美食
     * 02	时尚购物
     * 03	休闲娱乐
     * 04	生活服务
     * 05	汽车服务
     * 06  其它
     */
    private final static String[] ProductCategories = {"04"};

    /**
     * 4位  不用补全
     * 商品所属小类
     * 0002	经济酒店	00
     * 0001	度假酒店	00
     * 0000	星级酒店	00
     * 0101	西餐	    01
     * 0100	中餐	    01
     * 0102	海鲜	    01
     * 0106	面包糕点	01
     * 0107	其它餐饮	01
     * 0105	日韩料理	01
     * 0103	烧烤	    01
     * 0104	咖啡	    01
     * 0204	数码电器	02
     * 0205	母婴专卖	02
     * 0206	体育户外	02
     * 0203	家居用品	02
     * 0200	超市百货	02
     * 0201	服装服饰	02
     * 0202	烟酒茶叶	02
     * 0207	珠宝首饰	02
     * 0212	鲜花礼品	02
     * 0213	品牌专卖	02
     * 0214	其它购物	02
     * 0211	纪念特产	02
     * 0208	钟表眼镜	02
     * 0209	五金建材	02
     * 0210	文化用品	02
     * 0301	KTV	        03
     * 0302	赛马	    03
     * 0300	高尔夫	    03
     * 0305	影院剧场	03
     * 0304	酒吧茶馆	03
     * 0307	保健养生	03
     * 0308	会所俱乐部	03
     * 0306	运动健身	03
     * 0303	旅游	    03
     * 0309	其它娱乐	03
     * 0407	其它服务	04
     * 0402	票务代理	04
     * 0403	教育培训	04
     * 0404	影印拍摄	04
     * 0401	洗衣清洁	04
     * 0406	医疗医药	04
     * 0405	保险销售	04
     * 0400	美容美发	04
     * 0502	驾驶培训	05
     * 0503	其它汽车服务05
     * 0500	销售维护	05
     * 0501	租车服务	05
     */
    private final static String[] ProductTypes = {"0407"};

    /**
     * 10位
     * 可使用商户id
     */
    private final static String MerchantId = "12565";

    /**
     * 获取商户id
     * 命名规则为MXXXXNNNNN
     * (XXXX为四位第三方系统名称，NNNNN为数字编码，范围为00001~99999)
     *
     * @return
     */
    public static String getMerchantId() throws Exception {
        return "M" + BOCCCConstant.MERCHANT_NAME + BOCCCUtils.complete(MerchantId, '0', true, 5);
    }


    /**
     * 11位  需要补全
     * 价格  格式带两位小数位，例如：345.00
     */
    private final static String[] ProductPrices = {"345.00"};

    /**
     * 11位  需要补全
     * 积分
     */
    private final static String[] ProductPoints = {"100"};

    /**
     * 10位  不需要补全
     * 有效期开始日期 YYYY-MM-DD，如果为空则从即日开始
     */
    private final static String EffectBeginDate = "2019-09-01";

    /**
     * 10位  不需要补全
     * 有效期开始日期 YYYY-MM-DD，如果为空则为永久有效
     */
    private final static String EffectEndDate = "2021-08-31";

    /**
     * 1位  不需要补全
     * 秒杀标志位  0：不秒杀，1：秒杀
     */
    private final static String secKill = "0";

    /**
     * 检查各个参数是否合法
     *
     * @throws Exception
     */
    public static void check() throws Exception {
        int count = ProductIds.length;
        if (ProductNames.length != count) {
            throw new Exception("ProductNames长度和ProductIds不匹配");
        }
        if (ProductCategories.length != count) {
            throw new Exception("ProductCategories长度和ProductIds不匹配");
        }
        if (ProductTypes.length != count) {
            throw new Exception("ProductTypes长度和ProductIds不匹配");
        }
        if (ProductPrices.length != count) {
            throw new Exception("ProductPrices长度和ProductIds不匹配");
        }
        if (ProductPoints.length != count) {
            throw new Exception("ProductPoints长度和ProductIds不匹配");
        }
//        Date beginDate = BOCCCUtils.dateFormat(EffectBeginDate);
//        Date endDate = BOCCCUtils.dateFormat(EffectEndDate);
//        if (endDate.compareTo(beginDate) > 0) {
//            throw new Exception("beginDate不能晚于endDate");
//        }
        if (!(secKill.equals("0") || secKill.equals("1"))) {
            throw new Exception("secKill只能为0或1");
        }

    }

    public static StringBuilder generateBin() throws Exception {
        StringBuilder sb = new StringBuilder();
        //商品个数
        int count = ProductIds.length;
        for (int i = 0; i < count; i++) {
            //添加商品id
            sb.append(getProductId(ProductIds[i])).append(BOCCCUtils.getSeparator());
            //添加商品名称
            sb.append(BOCCCUtils.complete(ProductNames[i], ' ', false, 40)).append(BOCCCUtils.getSeparator());
            //添加商品所属大类
            sb.append(ProductCategories[i]).append(BOCCCUtils.getSeparator());
            //添加商品所属小类
            sb.append(ProductTypes[i]).append(BOCCCUtils.getSeparator());
            //添加可使用商户
            sb.append(getMerchantId()).append(BOCCCUtils.getSeparator());
            //添加价格
            sb.append(BOCCCUtils.complete(ProductPrices[i], ' ', true, 11)).append(BOCCCUtils.getSeparator());
            //添加积分
            sb.append(BOCCCUtils.complete(ProductPoints[i], ' ', true, 11)).append(BOCCCUtils.getSeparator());
            //添加有效期开始日期
            sb.append(BOCCCUtils.complete(EffectBeginDate, ' ', false, 10)).append(BOCCCUtils.getSeparator());
            //添加有效期结束日期
            sb.append(BOCCCUtils.complete(EffectEndDate, ' ', false, 10)).append(BOCCCUtils.getSeparator());
            //添加秒杀标志
            sb.append(secKill).append(BOCCCUtils.getSeparator());
            //添加商品描述1-10
            String space200 = BOCCCUtils.generate(200, ' ');
            for (int j = 0; j < 10; j++) {
                sb.append(space200).append(BOCCCUtils.getSeparator());
            }
            //添加换行信息
            sb.append("\r\n");
        }
        //添加文件尾部信息
        sb.append(BOCCCUtils.generateFileEndChar(count));
        return sb;
    }

    /**
     * 源文件的文件路径
     *
     * @return
     */
    public static String generateSourceFileName() {
        return BOCCCConstant.rootPath + "WARES." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.dateFormat(new Date()) + ".00.P";
    }

    /**
     * 压缩文件的文件路径
     *
     * @return
     */
    public static String generateZipFileName() {
        return BOCCCConstant.rootPath + "WARES." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.dateFormat(new Date()) + ".00.P.ZIP";
    }

    /**
     * 加密文件文件路径
     *
     * @return
     */
    public static String generatePgpFileName() {
        return BOCCCConstant.rootPath + "WARES." + BOCCCConstant.MERCHANT_NAME + "." + BOCCCUtils.dateFormat(new Date()) + ".00.P.ZIP.DAT";
    }


    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {
        //第一步，检查各个参数是否合法
        check();
        //第二步，按照规则组成商品信息字符串
        StringBuilder sb = generateBin();
        //第三步，写入文件中
        logger.info("源文件生成中...");
        String sourceFileName = generateSourceFileName();
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("源文件生成完成，路径为：" + sourceFileName);
        //第四步，源文件压缩中
        logger.info("源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        String zipFileName = generateZipFileName();
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("源文件压缩完成，路径为：" + zipFileName);
        //第五步，压缩文件加密中
        String pgpFileName = generatePgpFileName();
        logger.info("压缩文件加密中...");
        //TODO
        logger.info("压缩文件加密完成，路径为：" + pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }

}
