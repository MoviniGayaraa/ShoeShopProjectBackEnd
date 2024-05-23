package lk.ijse.gdse.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.service.JWTService;
import lk.ijse.gdse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Configuration
@RequiredArgsConstructor
public class JWTConfiguarationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String userEmail;
        final String jwt;

        String authorization = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer " )) {
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authorization.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (StringUtils.isNoneEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            //Validation of Token Status
            if (jwtService.validateToken(jwt, userDetails)) {
                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource());
                emptyContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(emptyContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
