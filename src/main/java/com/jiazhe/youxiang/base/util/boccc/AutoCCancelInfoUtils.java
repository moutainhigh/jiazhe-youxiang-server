package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * @author TU
 * @description 模拟生成退货文件，实际生产中不放入定时任务
 * @date 2019-09-05.
 */
@Component
public class AutoCCancelInfoUtils {

    public static Logger logger = LoggerFactory.getLogger(AutoCCancelInfoUtils.class);

    public static AutoCCancelInfoUtils cCancelInfoUtils;

    @Autowired
    private VoucherExchangeCodeService voucherExchangeCodeService;

    @PostConstruct
    public void init() {
        cCancelInfoUtils = this;
        cCancelInfoUtils.voucherExchangeCodeService = this.voucherExchangeCodeService;
    }

    /**
     * 模拟获取退货的代金券
     *
     * @param type
     * @return
     */
    public static List<VoucherExchangeCodeDTO> getYesterdayUsed(String type) {
//        List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList = cCancelInfoUtils.voucherExchangeCodeService.getYesterdayUsed(type);
//        return voucherExchangeCodeDTOList;
        return null;
    }

    public static StringBuilder generateBin(List<VoucherExchangeCodeDTO> list) throws Exception {

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (VoucherExchangeCodeDTO dto : list) {
            sb.append(BOCCCUtils.complete(String.valueOf(i), '0', true, 19)).append(BOCCCConstant.BOC_Separator);
//            sb.append(dto.getBocccProductId()).append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete(String.valueOf(dto.getId()), '0', true, 10)).append(BOCCCConstant.BOC_Separator);
            sb.append("E").append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete(dto.getKeyt(), '0', true, 36)).append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete("", ' ', false, 1)).append(BOCCCConstant.BOC_Separator);
            sb.append("20190901").append(BOCCCConstant.BOC_Separator);
            sb.append("19:23:23").append(BOCCCConstant.BOC_Separator);
            sb.append(BOCCCUtils.complete("", ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
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
     * 根据以上信息，生成昨日已使用优惠券加密压缩文件
     *
     * @return
     */
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_SOURCE, -1);
        String zipFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_ZIP, -1);
        String pgpFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_PGP, -1);

        //第一步，获取昨日退货的代金券兑换码
        List<VoucherExchangeCodeDTO> list = getYesterdayUsed("1");

        //第二步，按照规则组成昨日退货的代金券兑换码字符串
        if (list.isEmpty()) {
            logger.info("昨日代金券退货数量为：0");
            return;
        }
        logger.info("昨日代金券退货数量为：" + list.size());
        //代金券退货数量不为0，则在根目录下去生成文件
        StringBuilder sb = generateBin(list);

        //第三步，写入文件中
        logger.info("昨日退货代金券源文件生成中...");
        BOCCCUtils.writeStringToFile(sourceFileName, sb.toString());
        logger.info("昨日退货代金券源文件生成完成，路径为：" + sourceFileName);

        //第四步，源文件压缩中
        logger.info("昨日退货代金券源文件压缩中...");
        File sourceFile = new File(sourceFileName);
        new ZipUtil(new File(zipFileName)).zipFiles(sourceFile);
        logger.info("昨日退货代金券源文件压缩完成，路径为：" + zipFileName);

        //第五步，压缩文件加密中
        logger.info("昨日退货代金券压缩文件加密中...");
        PgpEncryUtil.Encry(zipFileName, BOCCCConstant.publicKeyPath, pgpFileName);
        logger.info("昨日退货代金券压缩文件加密完成，路径为：" + pgpFileName);
    }
}
