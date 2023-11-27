package com.example.kifiyaassessment.models;

public class GenericModel<T> {

    public T object[];

    public GenericModel(T[] object) {
        this.object = object;
    }

    public T[] getObject() {
        return object;
    }

    public void setObject(T[] object) {
        this.object = object;
    }
}
