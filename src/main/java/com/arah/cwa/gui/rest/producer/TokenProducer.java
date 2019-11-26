package com.arah.cwa.gui.rest.producer;

import com.arah.cwa.gui.rest.entity.JwtToken;
import com.arah.cwa.gui.rest.entity.UserCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class TokenProducer extends Producer {

    @Value("${backend.path.token}")
    private String tokenPath;

    public JwtToken authenticate(UserCredentials userCredentials) {
        URI tokenUri = getClearUri(tokenPath);
        return getOneObject(tokenUri, HttpMethod.POST, userCredentials, JwtToken.class);
    }

    public Boolean isValidToken(JwtToken jwtToken) {
        URI tokenUri = getClearUri(tokenPath);
        return getOneObject(tokenUri, HttpMethod.PUT, jwtToken, Boolean.class);
    }
}
