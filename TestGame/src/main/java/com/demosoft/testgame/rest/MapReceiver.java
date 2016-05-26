package com.demosoft.testgame.rest;

import com.demosoft.testgame.map.enity.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Andrii_Korkoshko on 5/24/2016.
 */
@Component
public class MapReceiver {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("jacksonObjectMapper")
    private MyJsonMapper myJsonMapper;


    public Map getMap() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<java.lang.String> responseEntity = restTemplate.exchange("http://localhost:8080/map/cell/all", HttpMethod.GET, entity, String.class);
        Map.MapTransportBean mapTransportBean = null;
        try {
            mapTransportBean = myJsonMapper.readValue(responseEntity.getBody(), new TypeReference<Map.MapTransportBean>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Map(mapTransportBean);
    }


}
