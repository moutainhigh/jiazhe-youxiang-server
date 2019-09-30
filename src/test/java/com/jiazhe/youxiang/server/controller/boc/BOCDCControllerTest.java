package com.jiazhe.youxiang.server.controller.boc;

import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.base.util.ShaUtils;
import org.junit.Test;

import java.net.URLEncoder;

/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
public class BOCDCControllerTest {


    private static String BOCDC_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCB0iSuZYgNRIAK85PRw1vHHa5bHrCP2+zf4RlorO+1otQ/Kd+k6JciNI2/aJQmQoaebY7l1woM7dNc1/Lvur+KYORMecVBwRh0J64pd8IdlUVnPPs5gAOkaWr6iDtGO6RLvsT1Avq/ZKcEoskGmdozmcvvcUcr3urfS7RflW5kSwIDAQAB";

    @Test
    //根据公钥加密,根据私钥解密
    public void testRsaDecpoint() throws Exception {
        String orderno = "1000004642";
        String giftno = "IGI1000006780";

        //公钥字符串
        String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCB0iSuZYgNRIAK85PRw1vHHa5bHrCP2+zf4RlorO+1otQ/Kd+k6JciNI2/aJQmQoaebY7l1woM7dNc1/Lvur+KYORMecVBwRh0J64pd8IdlUVnPPs5gAOkaWr6iDtGO6RLvsT1Avq/ZKcEoskGmdozmcvvcUcr3urfS7RflW5kSwIDAQAB";
        //根据字符串公钥生成公钥
        //PublicKey uppPubKey = RSAUtils1024.getPublicKeyFromString(pubKeyStr);

        //String orderNoNew=RSAUtils.encodeByPublicKeyFormat(orderno,pubKeyStr);
        //String giftNoNew=RSAUtils.encodeByPublicKeyFormat(giftno,pubKeyStr);

        String orderNoNew = RSAUtil.publicEncrypt(orderno, pubKeyStr);

        String giftNoNew = RSAUtil.publicEncrypt(giftno, pubKeyStr);
        System.out.println("加密之后：orderNoNew" + orderNoNew + "==>giftNoNew" + giftNoNew);
        String data = "<data>" +
                "<orderNo>" + orderNoNew + "</orderNo>" +
                "<tranDate>" + 20190620 + "</tranDate>" +
                "<orderStatus>04</orderStatus>" +
                "<validDate>" + 20190620 + "</validDate>" +
                "<giftNo>" + giftNoNew + "</giftNo>" +
                "</data>";
        String xmlStr = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>" + data;
//        String sign = JFSHA256.SHADigest(xmlStr);
        String sign = ShaUtils.getSha256(xmlStr);
        //encode 防止特殊字符
        xmlStr = URLEncoder.encode(xmlStr, "utf-8");
        System.out.println("data==?" + xmlStr);


        System.out.println("encode转换字符之后:" + xmlStr);
        //根据私钥解密
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIHSJK5liA1EgArzk9HDW8cdrlsesI/b7N/hGWis77Wi1D8p36TolyI0jb9olCZChp5tjuXXCgzt01zX8u+6v4pg5Ex5xUHBGHQnril3wh2VRWc8+zmAA6RpavqIO0Y7pEu+xPUC+r9kpwSiyQaZ2jOZy+9xRyve6t9LtF+VbmRLAgMBAAECgYADv/MpSmt8np7oMhLx+yeaf8P6g89ryFl2OgVG3b/qyjJBRM9d7w32gfgaMEYfOMq0pst63hQ/PVtekipFPGqt5Pekf5LgyWkCQ02sNxJt8j+Hjy0Qe+2ehzrT5KsiDWX4Rc5Ju7ZJkKu4jc6kpMP8+XJkVCnVQIfVrnyFAAGvEQJBAMvbO+RfuQnDmWqixpVeSPFq6Detjz3d23c4X8yL6mlpo369DBrcdGcNpJLWkNoJaMmlR5wvhh2yvi44TBicbKkCQQCjBvkoneDZtXTdPTfHZBk/cdmrBGnjMtdNKamKufHcO8iaBYmzglRT1yW0XcaQiHoE19gJRW+cjw6LZ1HCgk3TAkBb1TgmyeRefKnY+tkAosezUZiotFvp386k51VonMMNl5QaXOleFF+vqNqxd00rm7+DzxvyRQpiyImZ4qyNDbJ5AkBtDf0ebn47JGdIl+r4Nr2s8zvoastqcFgWBgikBD8GU/edEJdMvaIrc4CFZzWs+Z6mHr0ldo1y7T+NgU/wDbqBAkBY6jlylK4X8qLVVhUGGByyEFArobmBKA3XynwVNSypndHTmZq24bq6S7z7LVfm+GAz9i2pOYoGlJ8y4zXQM0fm";

//        String giftnojimi = RSAUtils1024.decodeByPrivateKeyFormat(giftNoNew, privateKey);
//        String ordernojimi = RSAUtils1024.decodeByPrivateKeyFormat(orderNoNew, privateKey);
        String giftnojimi = RSAUtil.privateDecrypt(giftNoNew, privateKey);
        String ordernojimi = RSAUtil.privateDecrypt(orderNoNew, privateKey);

        System.out.println("解密之后:giftnojimi=>" + giftnojimi + "==>ordernojimi" + ordernojimi);
        System.out.println("验签sign:" + sign);
    }

    @Test
    public void testSha() {
        String param = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>" +
                "<data>" +
                "<orderNo>JQmsvCw1MSrILK/es/lcMzMeQIutgo5RKfQfuDQKy4RTWhSngAQxM3uD5YSM2Czut23kvhXmil2DWoUK0Q0QblQ2BnPmSJGrSbKW6EpIfnEs9kWxVdeYgrr8VO8ONXzmk6nL5yxaetKozVQM+OpIEpMCkMEMsA4dM38zjEcgAAA=</orderNo>" +
                "<tranDate>20190620</tranDate>" +
                "<orderStatus>04</orderStatus>" +
                "<validDate>20190620</validDate>" +
                "<giftNo>TxQyYWQLFMA1A91fG1Y/8enicufa1XQPglSzoU+Cf+CDD38YZagxjTPj7YUe3T1iJJLyBQ7kTIGU2gc3HepU1Aa+WZB4JRLYGF2bhUfIRuxYh7Hmce7Mh+0SBuTadMPFe7VayRy0YwscT9KrUjSjiGF7FcF0ZASEkEmdySSIDZQ=</giftNo>" +
                "</data>";
        System.out.println("验签:sign: " + ShaUtils.getSha256(param));
    }

    @Test
    public void testDecrypt() throws Exception {
        //根据私钥解密
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIHSJK5liA1EgArzk9HDW8cdrlsesI/b7N/hGWis77Wi1D8p36TolyI0jb9olCZChp5tjuXXCgzt01zX8u+6v4pg5Ex5xUHBGHQnril3wh2VRWc8+zmAA6RpavqIO0Y7pEu+xPUC+r9kpwSiyQaZ2jOZy+9xRyve6t9LtF+VbmRLAgMBAAECgYADv/MpSmt8np7oMhLx+yeaf8P6g89ryFl2OgVG3b/qyjJBRM9d7w32gfgaMEYfOMq0pst63hQ/PVtekipFPGqt5Pekf5LgyWkCQ02sNxJt8j+Hjy0Qe+2ehzrT5KsiDWX4Rc5Ju7ZJkKu4jc6kpMP8+XJkVCnVQIfVrnyFAAGvEQJBAMvbO+RfuQnDmWqixpVeSPFq6Detjz3d23c4X8yL6mlpo369DBrcdGcNpJLWkNoJaMmlR5wvhh2yvi44TBicbKkCQQCjBvkoneDZtXTdPTfHZBk/cdmrBGnjMtdNKamKufHcO8iaBYmzglRT1yW0XcaQiHoE19gJRW+cjw6LZ1HCgk3TAkBb1TgmyeRefKnY+tkAosezUZiotFvp386k51VonMMNl5QaXOleFF+vqNqxd00rm7+DzxvyRQpiyImZ4qyNDbJ5AkBtDf0ebn47JGdIl+r4Nr2s8zvoastqcFgWBgikBD8GU/edEJdMvaIrc4CFZzWs+Z6mHr0ldo1y7T+NgU/wDbqBAkBY6jlylK4X8qLVVhUGGByyEFArobmBKA3XynwVNSypndHTmZq24bq6S7z7LVfm+GAz9i2pOYoGlJ8y4zXQM0fm";

        String param = "JQmsvCw1MSrILK/es/lcMzMeQIutgo5RKfQfuDQKy4RTWhSngAQxM3uD5YSM2Czut23kvhXmil2DWoUK0Q0QblQ2BnPmSJGrSbKW6EpIfnEs9kWxVdeYgrr8VO8ONXzmk6nL5yxaetKozVQM+OpIEpMCkMEMsA4dM38zjEcgAAA=";
        System.out.println("验签:sign: " + RSAUtil.privateDecrypt(param, privateKey));
    }

    @Test
    public void testBocdcDecrypt() throws Exception {
        String orderNo = "2016121200";
        String userId = "TC20001923535";
        String tranDate = "20170808";
        String orderStatus = "04";
        String giftNo = "IGI022566255";
        String validDate = "90";
        String template = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<bizdata><data>" +
                "<orderNo>%s</orderNo>" +
                "<userId>%s</userId>" +
                "<tranDate>%s</tranDate>" +
                "<orderStatus>%s</orderStatus>" +
                "<giftNo>%s</giftNo>" +
                "<validDate>%s</validDate>" +
                "</data></bizdata>";
        String before = String.format(template, orderNo, userId, tranDate, orderStatus, giftNo, validDate);
        orderNo = RSAUtil.publicEncrypt(orderNo, BOCDC_PUBLIC_KEY);
        giftNo = RSAUtil.publicEncrypt(giftNo, BOCDC_PUBLIC_KEY);
        String after = String.format(template, orderNo, userId, tranDate, orderStatus, giftNo, validDate);
        after = after.trim().replaceAll("\r|\n", "");
        String sign = ShaUtils.getSha256(after);

        System.out.println("加密前：param=" + before);
        System.out.println("加密后：param=" + after + "&sign=" + sign);

    }

    @Test
    public void testBocdcDecrypt2() throws Exception {
        String orderNo = "2016121200";
        String channel = "02";
        String ebuyId = "4E323916DE880098E05316BCC468D1C0";
        String template = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<bizdata><data>" +
                "<orderNo>%s</orderNo>" +
                "<channel>%s</channel>" +
                "<ebuyId>%s</ebuyId>" +
                "</data></bizdata>";
        String before = String.format(template, orderNo, channel, ebuyId);
        orderNo = RSAUtil.publicEncrypt(orderNo, BOCDC_PUBLIC_KEY);
        String after = String.format(template, orderNo, channel, ebuyId);
        after = after.trim().replaceAll("\r|\n", "");
        String sign = ShaUtils.getSha256(after);

        System.out.println("加密前：param=" + before);
        System.out.println("加密后：param=" + after + "&sign=" + sign);

    }
    @Test
    public void test1() {
        String param = "<?xml version=\"1.0\" encoding=\"UTF-8\"  standalone=\"yes\"?><bizdata><data><orderNo>qv0G1h/8mriyFr1KWwsfOuGOMUONGqsdyjZf5GF7EbWEn8YWwKmeOV7AwZj1ZlsTs3mvRcuHzWk+4vZ55Tg05IPrAXgW0XmKBHBvMdQcE3Iht8M1oc3h5S4Qy0uDiR+OgHZZyNGoNwY3KnyIvg+82cDw=</orderNo><useStatus>1</useStatus><useTime>2018/02/20</useTime><merId>ISI1000033150</merId></data></bizdata>";
        param = "<?xml version=\"1.0\" encoding=\"UTF-8\"  standalone=\"yes\"?><bizdata><data><orderNo>qsdM9ALGqQOPbd9RqNHayHeauCPGEE0h3iKDpbHOTkEh+1hcPXo3K8xAHKOFDW0AwlEg6oMGW4iyzNSLVRgzqggTeC7lyliaEOrGYGEO1HOcXFkR8opG8i2HZ+5t5/CzzqohozIA7fbSWc64uhaOOUSsCB8w84XSx7N+HEqOx8Q=</orderNo><useStatus>1</useStatus><useTime>2018/02/20</useTime><merId>ISI1000033150</merId></data></bizdata>";
        System.out.println("sgin = " + ShaUtils.getSha256(param));
    }
}