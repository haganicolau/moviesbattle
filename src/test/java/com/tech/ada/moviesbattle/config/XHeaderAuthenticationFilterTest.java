package com.tech.ada.moviesbattle.config;

import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.repository.PlayerRepository;
import com.tech.ada.moviesbattle.util.Util;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class XHeaderAuthenticationFilterTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private Util util;

    @InjectMocks
    private XHeaderAuthenticationFilter filter;

    @Test
    void doFilterInternal_ShouldAuthenticateUser_WhenValidCredentialsProvided() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String encodedCredentials = Base64.getEncoder().encodeToString("username:password".getBytes(StandardCharsets.UTF_8));
        when(request.getHeader("Authorization")).thenReturn("Basic " + encodedCredentials);

        Player player = new Player();
        player.setUsername("username");
        player.setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");

        when(playerRepository.findByUsernameAndPassword("username", "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8")).thenReturn(Optional.of(player));

        User user = new User("username", "password", Collections.singletonList(() -> "ROLE_USER"));
        UserDetails userDetails = User.withUsername("username").password("password").roles("USER").build();

        filter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(UsernamePasswordAuthenticationToken.class, SecurityContextHolder.getContext().getAuthentication().getClass());
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertEquals(userDetails, authenticationToken.getPrincipal());
        assertNull(authenticationToken.getCredentials());

        verify(filterChain).doFilter(request, response);
    }

    @Test
    void sendUnathorizedResponse_ShouldWriteErrorResponse_WhenResponseWriterIsNotNull() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        when(response.getWriter()).thenReturn(printWriter);

        filter.sendUnathorizedResponse(response, "User not found");

        printWriter.flush();
        String responseContent = outputStream.toString(StandardCharsets.UTF_8);
        assertTrue(responseContent.contains("{\"message\":\"User not found\"}"));
    }

    @Test
    void sendUnathorizedResponse_ShouldLogError_WhenResponseWriterIsNull() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        when(response.getWriter()).thenReturn(printWriter);

        filter.sendUnathorizedResponse(response, "User not found");

    }
}
