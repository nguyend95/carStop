package eu.epptec.carStop.security;

import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.service.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    UserServiceImpl userService;

    PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Map.Entry<Long, UserEntity> user = null;
        try {
            user = userService.loadUserByEmail(email);
        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("invalid login details");
        }

        if (!encoder.matches(user.getValue().getPassword(), password)){
            return null;
        }

        return createSuccessfulAuthentication(authentication, user.getValue());
    }

    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserEntity user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), authentication.getCredentials());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
