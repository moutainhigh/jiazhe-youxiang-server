package com.jiazhe.youxiang.base.util.boccc;

import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
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
    private PointExchangeCodeService pointExchangeCodeService;

    @PostConstruct
    public void init() {
        cCancelInfoUtils = this;
        cCancelInfoUtils.pointExchangeCodeService = this.pointExchangeCodeService;
    }

    public static StringBuilder generateBin() throws Exception {
        StringBuilder sb = new StringBuilder();
        List<BOCCCCouponUsedEntity> list = cCancelInfoUtils.pointExchangeCodeService.getBOCCCYesterdayUsed();
        if (list.isEmpty()) {
            sb.append(BOCCCUtils.generateFileEndChar(0));
        } else {
            int orderCode = 0;
            for (BOCCCCouponUsedEntity used : list) {
                orderCode++;
                sb.append(BOCCCUtils.complete(String.valueOf(orderCode), '0', true, 19)).append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(used.getGiftNo(), ' ', false, 11)).append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(String.valueOf(used.getId()), '0', true, 10)).append(BOCCCConstant.BOC_Separator);
                sb.append("R").append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete(used.getKeyt(), ' ', false, 36)).append(BOCCCConstant.BOC_Separator);
                sb.append(' ').append(BOCCCConstant.BOC_Separator);
                sb.append("20190901").append(BOCCCConstant.BOC_Separator);
                sb.append("19:23:23").append(BOCCCConstant.BOC_Separator);
                sb.append(BOCCCUtils.complete("", ' ', false, 200)).append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("").append(BOCCCConstant.BOC_Separator);
                sb.append("\r\n");
            }
            sb.append(BOCCCUtils.generateFileEndChar(list.size()));
        }
        return sb;
    }

    /**
     * 根据以上信息，模拟生成退货信息文件
     *
     * @return
     */
    @Deprecated
    public static void generateFile() throws Exception {

        //三种类型文件路径
        String sourceFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_SOURCE, -1);
        String zipFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_ZIP, -1);
        String pgpFileName = BOCCCConstant.rootPath + BOCCCUtils.getFileName(BOCCCConstant.BOC_CCANCEL_PGP, -1);

        StringBuilder sb = generateBin();

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
