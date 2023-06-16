package com.insearchofknowledge.bugTracker.configuration;

import com.insearchofknowledge.bugTracker.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor  // is for creating constructor that uses all Final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    // We intercept every request and extract data from the request and provide new data to the response
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain  // Chain of responsibility design pattern contains list of all other filters that we need
    ) throws ServletException, IOException {
        // We get the token from the header named "Authorization" from the incoming request headers
        final String authorizationHeader = request.getHeader("Authorization"); // when we make a call we need to pass the jwt authtToken within the header
        final String jwtToken;
        final String userEmail;
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) { // the authHeader always starts with the "Bearer" keyword
            filterChain.doFilter(request, response); // we call the rest of the filters, and we pass request and response to the next filters as params
            return; // we stop the execution of the code that follows after this
        }
        // if false we extract the JWT token which begins from index position 7 ("Bearer ")
        jwtToken = authorizationHeader.substring(7); // start after "Bearer "

        // we need to extract user email from JWT token
        userEmail = jwtService.extractUsername(jwtToken);

        // we make sure that user email is not null and that user is NOT authenticated yet
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);// if user is not authenticated we fetch userDetails from DB
            boolean isTokenFromDbValid = tokenRepository.findByToken(jwtToken)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwtToken, userDetails) && isTokenFromDbValid) {
                // if user and token are valid we need to update the security context and send the request to the dispatcher servlet
                // we create the UsernamePasswordAuthenticationToken object passing userDetails, credentials and authorities as params
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                        );
                // giving the authToken some more details, these details come from the http request
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // update security context holder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // as final step always call filterChain to pass the hand to the rest filters to be executed
        filterChain.doFilter(request, response);
    }
}
