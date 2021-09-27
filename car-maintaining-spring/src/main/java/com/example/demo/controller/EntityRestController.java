package com.example.demo.controller;


import com.example.demo.dto.JsonBody;
import com.example.demo.dto.ResponseMessage;
import com.example.demo.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EntityRestController {

    private EntityService entityService;

    @Autowired
    EntityRestController(EntityService entityService) {
        this.entityService = entityService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage addEntity(@RequestBody JsonBody jsonBody) {
        return entityService.addEntity(jsonBody);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseMessage updateEntity(@RequestBody JsonBody jsonBody, @PathVariable long id) {
        return entityService.updateEntity(jsonBody, id);
    }

    @RequestMapping(value = "/remove/{id}", method =  RequestMethod.PUT)
    public ResponseMessage removeCar(@PathVariable long id) {
        return entityService.removeCar(id);
    }


    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public ResponseMessage deleteCar(@PathVariable long id)
    {
        return entityService.deleteCar(id);
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public ResponseMessage deleteDriver(@PathVariable long id)
    {
        return entityService.deleteDriver(id);
    }
}