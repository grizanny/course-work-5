package com.arah.cwa.gui.security;

import com.arah.cwa.gui.rest.entity.JwtToken;
import com.arah.cwa.gui.rest.entity.User;
import com.arah.cwa.gui.rest.entity.UserCredentials;
import com.arah.cwa.gui.rest.producer.TokenProducer;
import com.arah.cwa.gui.rest.producer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityManagerImpl implements SecurityManager {

    private String currentToken;

    private User currentUser;

    @Autowired
    private TokenProducer tokenProducer;

    @Autowired
    private UserProducer userProducer;

    @Override
    public void refreshToken(UserCredentials userCredentials) {
        currentToken = tokenProducer.authenticate(userCredentials).getToken();
        currentUser = userProducer.findByLogin(userCredentials.getUsername());
    }

    @Override
    public boolean isValidToken() {
        return tokenProducer.isValidToken(new JwtToken(currentToken));
    }

    @Override
    public User currentUser() {
        return currentUser;
    }

    @Override
    public String currentToken() {
        return currentToken;
    }
}
