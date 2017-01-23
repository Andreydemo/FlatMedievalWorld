package com.demosoft.flatworld.json;

import com.demosoft.flatworld.map.enity.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 * Created by Andrii_Korkoshko on 5/24/2016.
 */
public class PointJsonDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String s, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Point point = new Point();
        point.setX(Integer.valueOf(s.substring(s.indexOf("x")+2,s.indexOf("|"))));
        point.setY(Integer.valueOf(s.substring(s.indexOf("y")+2)));
        return null;
    }

    public static class PointJsonDeserializerModule extends SimpleModule {
        public PointJsonDeserializerModule() {
            addKeyDeserializer(Point.class, new PointJsonDeserializer());
        }
    }

}


