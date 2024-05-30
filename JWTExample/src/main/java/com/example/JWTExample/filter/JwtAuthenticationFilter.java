package com.example.JWTExample.filter;


import com.example.JWTExample.helper.Jwthelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {//added to validate the jwt token which will be sent as part of header


    @Autowired
    private Jwthelper helper;
    @Autowired
    private UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        logger.info("received header param in filter  ", header);
        String userName = null;
        String jwtToken = null;
        if (header != null && header.startsWith("Bearer")) {
            jwtToken = header.substring(7);
            logger.info("jwttoken ",jwtToken);
            try {

                userName = this.helper.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
            } catch (Exception e) {
                logger.info("some generic exception has occurred ", e);

            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
                boolean validated = this.helper.validateToken(jwtToken, userDetails);
                if (validated) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                   // usernamePasswordAuthenticationToken.setAuthenticated(true);//no need to explicitly define instead the above constructor does the authentication process
                    logger.info("user authenticated "+userName+" is authenticated "+usernamePasswordAuthenticationToken.isAuthenticated());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }


            }

        } else {
            logger.info("invalid header value !!"+request.getMethod());
        }
        filterChain.doFilter(request, response);
    }
}
