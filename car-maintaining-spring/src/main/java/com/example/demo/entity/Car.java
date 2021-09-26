package com.example.demo.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * car entity has database properties and one to one  relation with driver
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "license_plate")
    @Size(min = 4, max = 12, message = "must be at least 4 and max 12")
    private String licensePlate;

    @Column(name = "convertible")
    private Boolean convertible;

    @Column(name = "rating")
    @Min(1) @Max(5)
    private Short rating;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date date;

    @OneToOne(mappedBy = "car", cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH})
    private Driver driver;

    public Car() {
    }

    public Car(String licensePlate, Boolean convertible, Short rating, String engineType, Date date, Driver driver, String manufacturer) {
        this.licensePlate = licensePlate;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.date = date;
        this.driver = driver;
        this.manufacturer =  manufacturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void add(Driver driver) {
        if (driver != null) {
            setDriver(driver);
            driver.setCar(this);
        }
    }
}
