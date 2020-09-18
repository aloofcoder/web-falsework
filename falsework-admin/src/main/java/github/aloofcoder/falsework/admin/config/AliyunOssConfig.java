package github.aloofcoder.falsework.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hanle
 * @date 2020/9/17 15:27
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {

    private String accessId;

    private String accessKey;

    private String endpoint;

    private String bucket;
}
