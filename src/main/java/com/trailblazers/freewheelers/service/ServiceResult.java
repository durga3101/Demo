package com.trailblazers.freewheelers.service;

public class ServiceResult<T extends Object> {
    private T model;

    public ServiceResult(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }
}
