//package com.tech.ada.moviesbattle.config;
//
//import com.tech.ada.moviesbattle.model.Player;
//import com.tech.ada.moviesbattle.repository.PlayerRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.io.IOException;
//import java.util.Base64;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import org.springframework.security.core.userdetails.User;
//
//public class XHeaderAuthenticationFilterTest {
//
//    @Mock
//    private PlayerRepository playerRepository;
//
//    @InjectMocks
//    private XHeaderAuthenticationFilter authenticationFilter;
//
//    @Test
//    public void testDoFilterInternal() throws ServletException, IOException {
//
//        MockitoAnnotations.initMocks(this);
//
//        String username = "user1";
//        String password = "password";
//        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        FilterChain filterChain = mock(FilterChain.class);
//        Player player = new Player();
//        player.setUsername(username);
//        player.setPassword(password);
//
//        when(request.getHeader("Authorization")).thenReturn("Basic " + encodedCredentials);
//        when(playerRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(player));
//        authenticationFilter.doFilterInternal(request, response, filterChain);
//
//        User expectedUser = new User(
//                username,
//                password,
//                true,
//                true,
//                true,
//                true,
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
//        UsernamePasswordAuthenticationToken expectedAuthentication =
//                new UsernamePasswordAuthenticationToken(expectedUser, null, expectedUser.getAuthorities());
////        verify(playerRepository, times(1)).findByUsernameAndPassword(username, password);
////        verify(filterChain, times(1)).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
//        assertEquals(expectedAuthentication, SecurityContextHolder.getContext().getAuthentication());
//    }
//}
