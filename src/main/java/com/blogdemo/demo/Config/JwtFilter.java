package com.blogdemo.demo.Config;

import com.blogdemo.demo.Entities.User;
import com.blogdemo.demo.Repository.UserRepository;
import com.blogdemo.demo.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    private UserRepository userRepository;

    public JwtFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String tokenVal = token.substring(8,token.length()-1);  // Corrected to 7 to extract the token properly
            System.err.println(tokenVal);
            String userName = jwtService.getUserName(tokenVal);
            System.err.println(userName);
            User user = userRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("username not found"));
            String role = user.getRole();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority(role)));
            auth.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.err.println("Authenticated User: " + SecurityContextHolder.getContext().getAuthentication());
        }
        filterChain.doFilter(request, response);
    }
}
