package com.demosoft.testgame.rest;


import com.demosoft.testgame.json.PointJsonDeserializer;
import com.demosoft.testgame.map.enity.Point;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by Andrii_Korkoshko on 5/23/2016.
 */
public class MyJsonMapper extends ObjectMapper {

    public MyJsonMapper() {
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.registerModule(new PointJsonDeserializer.PointJsonDeserializerModule());
    }
}