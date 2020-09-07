package github.aloofcoder.falsework.admin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanle
 */
@OpenAPIDefinition(
        info = @Info(
                title = "通用基础系统API",
                description = "通用基础系统",
                version = "v0.0.1",
                license = @License(name = "Apache 2.0", url = "http://springdoc.org"),
                contact = @Contact(name = "韩乐", email = "hanl1946@163.com", url = "https://github.com/aloofcoder")
        )
)
@SecurityScheme(name = "Authorization",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfig {
}
