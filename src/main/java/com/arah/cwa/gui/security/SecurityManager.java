package com.arah.cwa.gui.security;

import com.arah.cwa.gui.rest.entity.User;
import com.arah.cwa.gui.rest.entity.UserCredentials;


public interface SecurityManager {

    void refreshToken(UserCredentials userCredentials);

    boolean isValidToken();

    User currentUser();

    String currentToken();
}
