package eu.epptec.carStop.service.impl;

import eu.epptec.carStop.dto.jwt.JwtResponseDto;
import eu.epptec.carStop.dto.user.UserLoginDTO;
import eu.epptec.carStop.security.service.UserDetailsServiceImpl;
import eu.epptec.carStop.utils.JwtUtils;
import eu.epptec.carStop.security.service.UserDetailsImpl;
import eu.epptec.carStop.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthServiceImpl(JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public JwtResponseDto login(final UserLoginDTO login) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        String jwtToken = jwtUtils.generateJwtToken(authentication);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(login.getEmail());

        return new JwtResponseDto(jwtToken);
    }
}
