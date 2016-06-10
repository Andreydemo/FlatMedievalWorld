package com.demosoft.testgameserver.service.entiry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
public class ServiceResponse<T> {

    private Object object;
    private String name;
    private boolean success;
    private List<String> errors = new ArrayList<>();

    public ServiceResponse(Object objects) {
        this.object = objects;
    }

    public ServiceResponse() {
    }

    public T getObject() {
        return (T) object;
    }

    public void setObject(T objects) {
        this.object = (Object) objects;
        name = objects.getClass().getSimpleName();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
