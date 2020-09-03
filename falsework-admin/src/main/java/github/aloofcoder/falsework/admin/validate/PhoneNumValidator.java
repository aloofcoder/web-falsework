package github.aloofcoder.falsework.admin.validate;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanle
 * @date 2020/6/10 15:56
 * 自定义验证手机号
 */
public class PhoneNumValidator implements ConstraintValidator<ValidPhoneNum, String> {

    private Pattern pattern;
    private Matcher matcher;

    public static final String PHONE_NUM_PATTERN = "^1(3|4|5|6|7|8|9)\\d{9}$";

    @Override
    public boolean isValid(String phoneNum, ConstraintValidatorContext constraintValidatorContext) {
        return validPhoneNum(phoneNum);
    }

    private boolean validPhoneNum(String phoneNum) {
        if (StringUtils.isBlank(phoneNum)) {
            return true;
        }
        pattern = Pattern.compile(PHONE_NUM_PATTERN);
        matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }


}
