package com.cstins.xpay.conf;

/**
 * @program: csti-ns
 * @description: 阿里支付相关设置
 * @author: 杨云龙
 **/
public class AliPayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092600601407";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCG3NXhqyCXlfHLuJu3RYecx1rZzju1tOkY953vyse5o/DFfr2jxUP3hZlyzBnn0EuWRm3sRSc91Ju8Sm6Hi9sh0kBhZvoW0j800EBO0FTxKIjE3yaeE5erHP+rD1gZAxpJjvWtRvVuEtia8qtiVE6CzG0O8vk2L1swSUKMrrYX6UPwJ6Q45HkQ5xtIIrLbobH2ayspNGRhLQi/NtvM0xGhTA4cemPEJGj+NLQT8WybGCydmoWnvgZS2D7w0gWTgffKIVsribOxmNeUYJBuwx6VX30xZniSxBIx90bdniqFaIOah7SptBHuVgfHSdy48VagKx/tGriE4LNdwPhsulstAgMBAAECggEAJ07q0FHuuAAub/CpAmwzsNCyfFWONdI7NtnFoQT73fIY1ZexcrCmtNit9zXiPx0JSw3QMFOfsnfrxJ/1Q3DSODNuLHW1Yz14ys4AUHiMqZXk920LdnvDc06KfrOEPBYZbWNL3GuYUbMQi2k75vcnwxwOlZLgbJi0/Yf868BotuMIb97xLmIhDMRDr8PQzc/xYn4Xsn/agGEII9MpcMPtGGctk2WrPEcFQUiyI0gz8sbBygD52ZR94vNJTs3+7fCsz+FApHQR4YiwTCnGwQVwZpPFEXYKCIwbnNOxayf0FW0mVGiwgvB492/LJ40vmAnXv+rsF1fMqqMeumTXZeHgSQKBgQDzWw6i5k14VSDzDag2c4pQw445fTJjjM2WBr8WoWpoADtiGGeYkFZZjWEfe5CRYwbOmZBFO2rEPE9mAyWxRbunZh8RmaY2pQ5o64DKv7CQ4bckoHC/jckqU/nPoK7uvCo4rnc82F+My6ZSRwQAQjqT+u6AKSkdYZI9JEl/eviY7wKBgQCN3q55W40scB31kci3ukmewqu0GKzVzpAQC6X9PlBTsVukPDXDiYu6XMc0em8uAHfNdFYbgd7idfBweVr7/LnrtZkMyJXBAEivgz20wpwQJ3JC5H3PjaYthFCiOCwB2GXHBzPc88JqYwgEvazTJYHFz7z1gZRv3wp4S3wBSLC1owKBgHHFPUd9IfTC37+hv+WNCLoPYruPw3H1Dtw31ThQSH3MtfZ7cFtMB4KgbSqZyxGdWT5XXrJvP8/0Xi5UBFgnQL7XKGlDCwj7C4yTpUekbHiffc66z1tOfyCx19VwyrfD8n1DW21vvuQFBYHrv3wbemVaaAw0sgsRZZv+DfpyVCyzAoGBAIsO0Fnn4RLDurnv8ZwVHwBctEzNOuw/knoH0OXPZJEEcV0kH1bQxYMgWaoYsE2Y69UYySYhQEUdcVXtvb7tU5e04pXjLtaE3roanCtH7gzvfVIQATmmEFLhNOAsywXsBKb3amCTFeeNtlhfSr2UMAWiwAaswsB9ERgkdSIVDBCdAoGAVEvN8rI+Enpc9wu+pg81igRoELnkmfTsq2KYn1+6EKAPYooXodX6AaDw4vbmK0+HiLR4cjebQVbq2hdXncnQ4D8RfcslEMKkS46iiWH4JW5UDS8PypfG07sttTIN6/uwIjeSIjj13W+GF6S23GDwOb8OCRo7Csht9vdproEMkb4=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwTVFsEpV24wfCgrRero0KIOdBIpl533Jt8M1vdqcsZjNOMmBoCRlOMy0W1da7FsJWuL3ASJy9C9K9pSq4rJiK27DPTTLYJhZP7PGD2QGw+zH4i9xBJ/lrVVtvOgKhCpfzyHpZu0Z0w6bugVnux1NsLt8NfmuRV/DYrL4RIUsw45DYDbE6mOwIgKyuVGLdwujrTqihLEe/ZLl9P5qICHJ7J57gNjfZ0V369oW0IlEYhZxdsVO4nSyMRk+CCDFONm8w7h6heXrR7l8Yq+w2qQHNJ7FyttfB/g1lqy60blCgWjdZ3yOyogTe0dnANnE2bleIHxgvSImtzrWSohBwqYYXwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8088/xpay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8088/xpay/receive";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}
