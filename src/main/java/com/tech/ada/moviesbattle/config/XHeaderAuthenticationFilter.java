package com.tech.ada.moviesbattle.config;

import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.repository.PlayerRepository;
import com.tech.ada.moviesbattle.util.Util;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class XHeaderAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(XHeaderAuthenticationFilter.class);
    private final PlayerRepository playerRepository;
    private final Util util;

    @Autowired
    public XHeaderAuthenticationFilter(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.util = new Util();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        LOG.info("Authentication filter. Trying to get credentials user from Authorization header.");

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            sendUnathorizedResponse(response, "User credentials not found in request");
            return;
        }

        String credentials = new String(Base64.getDecoder().decode(header.substring(6)),
                StandardCharsets.UTF_8);
        String[] parts = credentials.split(":");
        String username = parts[0];
        String password = util.hashPassword(parts[1]);

        LOG.info("Authentication filter. find By Username And Password");
        Optional<Player> optionalPlayer = playerRepository.findByUsernameAndPassword(username, password);
        if (optionalPlayer.isEmpty()) {
            sendUnathorizedResponse(response, "User not found with username: " + username);
            return;
        }

        User user = createUserCredential(optionalPlayer.get());
        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOG.info("Authentication filter. Create filterChain with request.");
        filterChain.doFilter(request, response);
    }

    private User createUserCredential(Player player) {
        LOG.info("Create User Credential.");
        return new User(
                player.getUsername(),
                player.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

    }

    public void sendUnathorizedResponse(HttpServletResponse response, String message) throws IOException {
        LOG.info("User not found in the database.");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        if (writer != null) {
            writer.write("{\"message\":\"" + message + "\"}");
        } else {
            LOG.error("PrintWriter is null. Unable to write response.");
        }

    }

}
