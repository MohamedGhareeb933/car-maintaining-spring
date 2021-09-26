package com.example.demo.dto;

import com.example.demo.entity.Car;
import com.example.demo.entity.Driver;

/**
 * that class acts as payload - json body that has objects for both car and driver
 */
public class JsonBody {

    private Car car;
    private Driver driver;

    public JsonBody() {
    }

    public JsonBody(Car car, Driver driver) {
        this.car = car;
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
