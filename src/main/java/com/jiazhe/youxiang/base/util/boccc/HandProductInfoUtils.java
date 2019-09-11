package com.jiazhe.youxiang.base.util.boccc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author TU
 * @description 商品信息工具类
 * @date 2019-09-02.
 */
public class HandProductInfoUtils {

    public static Logger logger = LoggerFactory.getLogger(HandProductInfoUtils.class);

    /**
     * 11位
     * 商品的唯一编码，命名规则为WXXXXNNNNNN
     * (XXXX为四位第三方系统名称，NNNNNN为数字编码，范围为000001~999999)
     */
    private final static String[] ProductIds = {"1", "2", "3", "4"};

    public static String getProductId(String id) throws Exception {
        return "W" + BOCCCConstant.MERCHANT_NAME + BOCCCUtils.complete(id, '0', true, 6);
    }

    /**
     * 40位   需要补全
     * 商品中文名称
     */
    private final static String[] ProductNames = {"日常保洁", "擦玻璃", "皮沙发护理", "家电清洗"};

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
    private final static String ProductCategories = "04";

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
    private final static String ProductTypes = "0401";


    /**
     * 11位  需要补全
     * 价格  格式带两位小数位，例如：345.00
     */
    private final static String[] ProductPrices = {"345.00", "345.00", "345.00", "345.00"};

    /**
     * 11位  需要补全
     * 积分
     */
    private final static String[] ProductPoints = {"100", "100", "100", "100"};

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
            sb.append(getProductId(ProductIds[i])).append(BOCCCConstant.BOC_Separator);
            //添加商品名称
            sb.append(BOCCCUtils.complete(ProductNames[i], ' ', false, 40)).append(BOCCCConstant.BOC_Separator);
            //添加商品所属大类
            sb.append(ProductCategories).append(BOCCCConstant.BOC_Separator);
            //添加商品所属小类
            sb.append(ProductTypes).append(BOCCCConstant.BOC_Separator);
            //添加可使用商户
            sb.append(BOCCCConstant.MERCHANT_ID).append(BOCCCConstant.BOC_Separator);
            //添加价格，价格为空
            sb.append(BOCCCUtils.complete("", ' ', true, 11)).append(BOCCCConstant.BOC_Separator);
            //添加积分
            sb.append(BOCCCUtils.complete(ProductPoints[i], ' ', true, 11)).append(BOCCCConstant.BOC_Separator);
            //添加有效期开始日期
            sb.append(EffectBeginDate).append(BOCCCConstant.BOC_Separator);
            //添加有效期结束日期
            sb.append(EffectEndDate).append(BOCCCConstant.BOC_Separator);
            //添加秒杀标志
            sb.append(secKill).append(BOCCCConstant.BOC_Separator);
            //添加商品描述1-10
            String space200 = BOCCCUtils.generate(200, ' ');
            for (int j = 0; j < 10; j++) {
                sb.append(space200).append(BOCCCConstant.BOC_Separator);
            }
            //预留字段还未拼接
            sb.append("").append(BOCCCConstant.BOC_Separator);
            sb.append("").append(BOCCCConstant.BOC_Separator);
            sb.append("").append(BOCCCConstant.BOC_Separator);
            //添加换行信息
            sb.append("\r\n");
        }
        //添加文件尾部信息
        sb.append(BOCCCUtils.generateFileEndChar(count));
        return sb;
    }

    /**
     * 根据以上信息，生成商品信息加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径 day=-1表示昨日文件  0表示今日文件   1表示明日文件
        int day = 1;
        String sourceFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.WARES_SOURCE, day);
        String zipFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.WARES_ZIP, day);
        String pgpFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.WARES_PGP, day);

        //第一步，检查各个参数是否合法
        check();

        //第二步，按照规则组成商品信息字符串
        StringBuilder sb = generateBin();

        //第三步，写入文件中
        logger.info("商品信息源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("商品信息源文件生成完成，路径为：" + sourceFileName);

        //第四步，源文件压缩中
        logger.info("商品信息源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("商品信息源文件压缩完成，路径为：" + zipFileName);

        //第五步，压缩文件加密中
        logger.info("商品信息压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("商品信息压缩文件加密完成，路径为：" + pgpFileName);
    }

    public static void main(String[] args) throws Exception {
        generateFile();
    }

}
