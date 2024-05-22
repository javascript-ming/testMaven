package com.testJava.config.tencent;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class COSConfig {
    @Bean
    public COSClient cosClient() {
        // 设置用户属性, 包括secretId和secretKey等
        COSCredentials cred = new BasicCOSCredentials("", "");
        // 设置bucket的区域，例如ap-guangzhou、ap-shanghai等
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        return new COSClient(cred, clientConfig);
    }
}
