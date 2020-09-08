package github.aloofcoder.falsework.admin.controller;

import github.aloofcoder.falsework.admin.config.JwtTokenUtil;
import github.aloofcoder.falsework.admin.pojo.dto.UserLoginDTO;
import github.aloofcoder.falsework.common.util.AppException;
import github.aloofcoder.falsework.common.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanle
 * @date 2020-09-06
 */
@RestController
@Tag(name = "用户登录", description = "用户登录前端控制器")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Qualifier("customUserDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Operation(summary = "用户登录",
            responses = {
                    @ApiResponse(responseCode = "200", description = "登录返回Token", content = @Content(schema = @Schema(implementation = String.class)))
            })
    @PostMapping(value = "/login")
    public R createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new AppException("账号或密码错误");
        } catch (BadCredentialsException e) {
            throw new AppException("账号或密码错误");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return R.ok().put("token", token);
    }
}
