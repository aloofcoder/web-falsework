package github.aloofcoder.falsework.admin.config;

import github.aloofcoder.falsework.admin.pojo.bo.UserAuthBO;
import github.aloofcoder.falsework.common.util.AppException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 获取当前登录账号信息
 * @author hanle
 * @date 2020-09-06
 */
public class BaseContextUtil {

    public static UserAuthBO getLoginPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof UserAuthBO)) {
            throw new AppException("等路已失效");
        }
        UserAuthBO userBO = (UserAuthBO) authentication.getPrincipal();
        return userBO;
    }

    public static String getLoginNum() {
        return getLoginPrincipal().getUserNum();
    }

    public static String getLoginName() {
        return getLoginPrincipal().getLoginName();
    }

    public static List<String> getLoginRoles() {
        return getLoginPrincipal().getRoles();
    }
}
