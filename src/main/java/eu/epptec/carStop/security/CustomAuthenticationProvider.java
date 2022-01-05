package eu.epptec.carStop.security;

import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    final private UserService userService;
    final private PasswordEncoder encoder;

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<UserEntity> user;
        try {
            user = userService.get(email);
        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("invalid login details");
        }

        if (user.isEmpty() ||
            !encoder.matches(user.get().getPassword(), password)){
            return null;
        }

        return createSuccessfulAuthentication(authentication, user.get());
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
