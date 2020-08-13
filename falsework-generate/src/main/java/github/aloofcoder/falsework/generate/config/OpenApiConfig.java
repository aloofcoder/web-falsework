package github.aloofcoder.falsework.generate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "代码生成系统API",
                description = "代码生成系统",
                version = "v0.0.1",
                license = @License(name = "Apache 2.0", url = "http://springdoc.org"),
                contact = @Contact(name = "韩乐", email = "hanl1946@163.com", url = "https://github.com/aloofcoder")
        )
)
@Configuration
public class OpenApiConfig {
}
