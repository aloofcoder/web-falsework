package github.aloofcoder.falsework.admin.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author hanle
 * @date 2020/6/10 15:55
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneNumValidator.class)
@Documented
public @interface ValidPhoneNum {

    String message() default "Invalid Phone Num";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
