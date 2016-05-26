package com.demosoft.testgameserver.service.entiry;

import java.util.List;
import java.util.Objects;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
public class ServiceResponse<T> {

    private Objects objects;
    private boolean success;
    private List<String> errors;

    public ServiceResponse(Objects objects) {
        this.objects = objects;
    }

    public ServiceResponse() {
    }

    public T get() {
        return (T) objects;
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
}
