package github.aloofcoder.falsework.admin.pojo.bo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author hanle
 * @date 2020-09-06
 */
@Data
public class UserAuthBO implements UserDetails {

    private String userNum;

    private String loginName;

    private String loginPwd;

    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.loginPwd;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
