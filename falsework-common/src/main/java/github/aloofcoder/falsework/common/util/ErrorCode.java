package github.aloofcoder.falsework.common.util;

/**
 * @author hanle
 * @date 2020/4/30 9:17
 */
public enum ErrorCode {

    // 服务相关
    OK("00000", "OK"),
    USER_REQ_ERR("A0001", "用户端错误"),
    DB_REQ_ERR("A0002", "系统错误，请重试"),
    SERVER_ERR("A0003", "服务端错误"),
    // 用户相关
    USER_REGISTER_ERR("A0100", "用户注册错误"),
    USER_UN_AGREE_PROTOCOL("A0101", "用户未同意隐私协议"),
    USER_NAME_CHECK_ERR("A0102", "用户名校验失败"),
    USER_NAME_REPEAT("A0103", "系统已存在该用户名"),
    USER_NAME_SENSITIVE("A0104", "用户名包含敏感词"),
    USER_NUM_ERR("A0105", "无效的用户编号"),
    USER_DEL_ADMIN_ERR("A0106", "管理员账号不能被删除"),
    USER_ROLE_INVALID("A0107", "当前账号未分配角色，请联系管理员"),
    USER_INVALID_ERR("A0108", "用户账户不存在"),
    USER_PWD_ERR("A0109", "用户名或密码错误"),
    USER_THIRD_NON_AUTH("A0110", "用户未获得第三方登陆授权"),
    USER_AUTH_EXPIRED("A0111", "登陆已过期"),
    USER_VERIFY_CODE_ERROR("A0112", "用户验证码错误"),
    USER_VERIFY_CODE_OVERRUN("A0113", "用户验证码尝试次数超限"),
    USER_AUTH_ERROR("A0114", "访问权限异常"),
    USER_UN_AUTH("A0115", "登录未授权"),
    USER_AUTH_PROCESSING("A0116", "正在授权中"),
    USER_AUTH_REFUSE("A0117", "用户授权申请被拒绝"),
    USER_NON_API_AUTH("A0118", "无权限使用 API"),
    USER_PWD_EMPTY("A0119", "用户密码不能为空"),
    USER_EDIT_ADMIN_ERR("A0120", "管理员账号暂不支持修改"),
    // 角色相关
    ROLE_NAME_REPEAT("A0200", "系统已存在该角色名"),
    ROLE_MARK_REPEAT("A0201", "系统已存在该角色标记"),
    ROLE_ID_INVALID("A0202", "无效的角色"),
    ROLE_USED("A0203", "角色已分配给用户"),
    ROLE_EDIT_ADMIN_ERR("A0204", "管理员角色暂不支持修改"),
    // 组织相关
    ORG_ID_INVALID("A0300", "无效的组织"),
    ORG_NAME_REPEAT("A0301", "系统已存在该组织名"),
    ORG_PARENT_ID_INVALID("A0302", "无效的父组织Id"),
    ORG_USED("A0303", "该组织下有用户"),
    // 菜单相关
    MENU_ID_INVALID("A0400", "无效的菜单Id"),
    MENU_NAME_REPEAT("A0401", "系统已存在该菜单名"),
    MENU_PATH_REPEAT("A0402", "系统已存在该菜单路径"),
    MENU_PARENT_ID_INVALID("A0403", "无效的父菜单Id"),
    MENU_PATH_EMPTY("A0404", "菜单路径不能为空"),
    MENU_COMPONENT_ADDR_EMPTY("A0405", "菜单组件不能为空"),
    MENU_REDIRECT_ADDR_EMPTY("A0406", "菜单重定向路径不能为空"),
    MENU_MARK_EMPTY("A0407", "权限标记不能为空"),
    MENU_USED("A0408", "菜单已分配给角色"),
    MENU_HAS_CHILD("A0408", "菜单有子菜单"),
    // 资源相关
    SOURCE_ID_INVALID("A0500", "无效的资源Id"),
    SOURCE_USED("A0501", "资源已分配给角色"),
    SOURCE_NAME_REPEAT("A0502", "系统已存在该资源名"),
    SOURCE_URL_REPEAT("A0503", "系统已存在该资源路径"),
    // 数据字典
    DICT_NAME_REPEAT("A0601", "系统已存在该字典名称"),
    DICT_MARK_REPEAT("A0602", "系统已存在该字典标记"),
    DICT_ITEM_LABEL_REPEAT("A0621", "当前字典下已存在该字典标签"),
    DICT_ITEM_VALUE_REPEAT("A0622", "当前字典下已存在该字典值"),;

    private String code;

    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
