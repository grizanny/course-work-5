package com.arah.cwa.gui.rest.producer;

import com.arah.cwa.gui.rest.entity.User;
import com.arah.cwa.gui.rest.utils.UriUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Component
public class UserProducer extends Producer {

    @Value("${backend.path.user}")
    public String userPath;

    public User findByLogin(String login) {
        UriUtils.QueryParam queryParam = new UriUtils.QueryParam("login", new Object[]{login});
        URI uri = getUriWithParams(Collections.singletonList(queryParam), userPath);
        List<User> objectList = getObjectList(uri, HttpMethod.GET, null, User.class);
        return objectList.get(0);
    }

    public List<User> findAll() {
        URI uri = getClearUri(userPath);
        return getObjectList(uri, HttpMethod.GET, null, User.class);
    }
}
