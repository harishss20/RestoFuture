package com.example.server.security;

import com.example.server.model.Hotel;
import com.example.server.repository.HotelRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final HotelRepository hotelRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CustomOAuth2SuccessHandler(HotelRepository hotelRepository, JwtTokenProvider jwtTokenProvider) {
        this.hotelRepository = hotelRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String oauthId= (String) attributes.get("sub");

        hotelRepository.findByEmail(email).orElseGet(()->{
            Hotel hotel = new Hotel();
            hotel.setOwnerName(name);
            hotel.setEmail(email);
            hotel.setOauthProvider("GOOGLE");
            hotel.setOauthId(oauthId);
            return hotelRepository.save(hotel);
        });

        String token = jwtTokenProvider.generateToken(email);

        response.sendRedirect("http://localhost:3000/login/success?token=" + token);

    }


}