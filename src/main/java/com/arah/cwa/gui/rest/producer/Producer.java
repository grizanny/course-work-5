package com.arah.cwa.gui.rest.producer;

import com.arah.cwa.gui.exception.CallToExternalServiceException;
import com.arah.cwa.gui.rest.entity.User;
import com.arah.cwa.gui.rest.utils.UriUtils;
import com.arah.cwa.gui.security.SecurityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arah.cwa.gui.rest.utils.UriUtils.getUri;

@Slf4j
public abstract class Producer {

    private static final Map<Class, ParameterizedTypeReference> paramType = new HashMap<>();

    static {
        paramType.put(User.class, new ParameterizedTypeReference<List<User>>() {
        });
    }

    @Value("${backend.schema}")
    protected String schema;
    @Value("${backend.host}")
    protected String host;
    @Value("${backend.port}")
    protected String port;
    @Value("${backend.prefix}")
    protected String prefix;
    @Autowired
    private SecurityManager securityManager;

    protected URI getUriWithPathVariables(Object[] pathVariables, String... paths) {
        return getUri(pathVariables, Collections.emptyList(), schema, host, port, prefix, paths);
    }

    protected URI getUriWithParams(List<UriUtils.QueryParam> queryParams, String... paths) {
        return getUri(null, queryParams, schema, host, port, prefix, paths);
    }

    protected URI getClearUri(String... paths) {
        return getUri(null, Collections.emptyList(), schema, host, port, prefix, paths);
    }

    protected URI getUriWithParamsAndPathVariables(Object[] pathVariables, List<UriUtils.QueryParam> queryParams, String... paths) {
        return getUri(pathVariables, queryParams, schema, host, port, prefix, paths);
    }

    protected <T> T getOneObject(URI uri, HttpMethod method, Object body, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        log.info(uri.toString());
        try {
            ResponseEntity<T> response = restTemplate.exchange(
                    uri,
                    method,
                    attachAuthHeader(body),
                    clazz);

            return response.getBody();
        } catch (RestClientException e) {
            throw new CallToExternalServiceException(method, uri);
        }
    }

    @SuppressWarnings("all")
    protected <T> List<T> getObjectList(URI uri, HttpMethod method, Object body, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        log.info(uri.toString());
        try {
            ResponseEntity<List<T>> response = restTemplate.exchange(
                    uri,
                    method,
                    attachAuthHeader(body),
                    paramType.get(clazz));

            return response.getBody();
        } catch (RestClientException e) {
            log.error(e.getLocalizedMessage());
            throw new CallToExternalServiceException(method, uri);
        }
    }

    private HttpEntity attachAuthHeader(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(securityManager.currentToken());
        return body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
    }
}
