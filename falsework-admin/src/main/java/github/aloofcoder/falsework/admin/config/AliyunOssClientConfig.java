package github.aloofcoder.falsework.admin.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanle
 * @date 2020/9/17 15:58
 */
@Configuration
public class AliyunOssClientConfig {

    @Autowired
    private AliyunOssConfig ossConfig;

    @Bean
    public OSS ossClient() {
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessId(), ossConfig.getAccessKey());
        return ossClient;
    }
}
