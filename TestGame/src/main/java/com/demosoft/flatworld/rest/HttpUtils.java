package com.demosoft.flatworld.rest;

import com.demosoft.flatworld.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@Component
public class HttpUtils {

    public static final String JSESSIONID = "JSESSIONID";
    public static final String SET_COOKIE = "Set-Cookie";
    public static final String COOKIE = "Cookie";

    @Autowired
    private Player player;

    public void processFirstRequest(ResponseEntity responseEntity) {
        for (String s : responseEntity.getHeaders().get(SET_COOKIE).get(0).split(";")) {
            s = s.trim();
            if (s.contains(JSESSIONID)) {
                String sessionId = s.substring(s.indexOf("=") + 1);
                player.setJsessionid(sessionId);
            }
        }
    }

    public void prepareRequest(HttpHeaders httpHeaders) {
        httpHeaders.add(COOKIE, JSESSIONID + "=" + player.getJsessionid());
    }
}
