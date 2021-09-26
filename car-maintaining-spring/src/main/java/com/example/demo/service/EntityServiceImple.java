package com.example.demo.service;

import com.example.demo.dao.CarRepository;
import com.example.demo.dao.DriverRepository;
import com.example.demo.dto.JsonBody;
import com.example.demo.dto.ResponseMessage;
import com.example.demo.entity.Car;
import com.example.demo.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EntityServiceImple implements EntityService{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverRepository driverRepository;

    /**
     * add Entity - define response message - handle exceptions
     *
     * @param jsonBody
     * @exception Exception
     * @return ResponseMessage
     */
    @Override
    public ResponseMessage addEntity(JsonBody jsonBody) {

        String message = "Successfully added";

        try {
            handleAdd(jsonBody);
        }catch(Exception e) {
            message = "Car already has a Driver ";
            e.printStackTrace();
        }
        return new ResponseMessage(message);
    }

    /**
     * update car or driver
     * one object only could be updated at a time
     * because they have different id
     *
     * @param id
     * @param jsonBody
     * @throws Exception
     * @return ResponseMessage
     * */
    @Override
    public ResponseMessage updateEntity(JsonBody jsonBody, long id)  {
        String message = "no string";

        if (jsonBody.getCar() != null) {
            message = handleCarUpdate(jsonBody, id);
        }else if (jsonBody.getDriver() != null) {
            message = HandleDriverUpdate(jsonBody, id);
        } else {
            message = "Can't update - please input proper json";
        }
        return new ResponseMessage(message);
    }

    /**
     * remove driver from the car
     *
     * @param id
     * @return
     */
    @Override
    public ResponseMessage removeCar(long id) {

        driverRepository.findById(id).map(
                driver -> {
                    driver.setCar(null);

                    return driverRepository.save(driver);
                }
        );

        return new ResponseMessage("car Driver Removed");
    }

    /**
     *  find driver and get driver object from json
     *  update the driver object we got from find by id if driver json object property exist
     *  then save the updated driver
     *
     * @param jsonBody
     * @param id
     * @return message
     */
    private String HandleDriverUpdate(JsonBody jsonBody, long id) {

        AtomicReference<String> message = new AtomicReference<>("driver successfully Updated");

        driverRepository.findById(id).map(
                driver -> {
                    try {
                        Driver bodyDriver = jsonBody.getDriver();

                        if (bodyDriver.getCar() != null) driver.setCar(carDriver(jsonBody).getCar());

                        if (bodyDriver.getFirstName() != null) driver.setFirstName(bodyDriver.getFirstName());

                        if (bodyDriver.getLastName() != null) driver.setFirstName(bodyDriver.getLastName());

                        if (bodyDriver.getPhone() != null) driver.setPhone(bodyDriver.getPhone());

                    } catch (Exception e) {
                        e.printStackTrace();

                        message.set("car Already has a Driver");

                        return message.toString();
                    }
                    return driverRepository.save(driver);
                });

        return message.toString();
    }

    /**
     * find driver and get driver object from json
     * update the driver object we got from find by id if driver json object property exist
     * then save the updated driver
     *
     * @param jsonBody
     * @param id
     * @return
     */
    public String handleCarUpdate(JsonBody jsonBody, long id) {

        carRepository.findById(id).map(
                car -> {
                    Car jsonCar = jsonBody.getCar();

                    if (jsonCar.getLicensePlate() != null) car.setLicensePlate(jsonCar.getLicensePlate());

                    if (jsonCar.getConvertible() != null) car.setConvertible(jsonCar.getConvertible());

                    if (jsonCar.getRating() != null) car.setRating(jsonCar.getRating());

                    if (jsonCar.getRating() != null) car.setRating(jsonCar.getRating());

                    if (jsonCar.getEngineType() != null) car.setEngineType(jsonCar.getEngineType());

                    if (jsonCar.getManufacturer() != null) car.setManufacturer(jsonCar.getManufacturer());

                    return carRepository.save(car);
                }
        );

        return "Car Successfully updated ";
    }


    /**
     * get the car object from the body - and add driver in case of driver object exist and dosnt has car id.
     * otherwise search for the driver car id and save the driver
     *
     * @param jsonBody
     * */
    private void handleAdd(JsonBody jsonBody) throws Exception {

        if (jsonBody.getCar() != null) {
            Car car = jsonBody.getCar();
            if (jsonBody.getDriver() != null && jsonBody.getDriver().getCar() == null) {
                Driver driver = jsonBody.getDriver();
                car.add(driver);
            }
            carRepository.save(car);
        }else if(jsonBody.getDriver() != null) {
            Driver driver = carDriver(jsonBody);
            driverRepository.save(driver);
        }
    }

    /**
     * get the driver from the body and find the car that exists in driver object
     * and in case that car exists in the driver list throw an exception
     * otherwise save the driver
     *
     * @param jsonBody
     * @return driver
     * @throws Exception
     */
    private Driver carDriver(JsonBody jsonBody) throws Exception {

        Driver driver = jsonBody.getDriver();
        Car driverCar = findCar(driver);

        List<Car> carList = new ArrayList<>();

        driverlist().forEach(
                driverIndex-> {
                    carList.add(driverIndex.getCar());
                });

        if (carList.contains(driverCar)) {
            throw new Exception("this Car Already has A Driver ");
        }

        driver.setCar(driverCar);

        return driver;
    }

    
    private Car findCar(Driver driver) {
        return carRepository.getById(driver.getCar().getId());
    }

    private List<Driver> driverlist() {
        return driverRepository.findAll();
    }

}
