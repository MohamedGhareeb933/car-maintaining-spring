package com.example.demo.service;

import com.example.demo.dto.JsonBody;
import com.example.demo.dto.ResponseMessage;

public interface EntityService {

    public ResponseMessage addEntity(JsonBody jsonBody);
    
    public ResponseMessage updateEntity(JsonBody jsonBody, long id);
    
    public ResponseMessage removeCar(long id);

    public ResponseMessage deleteCar(long id);

    public ResponseMessage deleteDriver(long id);

}
