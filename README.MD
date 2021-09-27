# car-maintaining-spring

this project was generated with java, spring, and maven

## build - install 

assuming you have java and maven installed globally in your computer and also MySQL 

open `db-scripts` file and run the following scripts 

first: **create-user.sql**

second : **create-schema-table.sql**  

third : **predefined-data.sql**

## run 

open teminal and `cd` to car-maintaining-spring file then run -> `mvn spring-boot:run`

or from your IDE navigate to `car-maintaining-spring/car-maintaining-spring/src/main/java/com/example/demo/CarMaintainingSpringApplication.java` and `run`

## development server 

after you build and run the project

head to localhost:8080/ 

note that the project has spring security authentication and authorization 

and login will be required 

## SQL Scripts Has : 

 - create-user.sql 
    - creating my SQL connection with username as *user* and password as *user*

    - you can change mysql connection user name and password but that will require you to change the user and  password of the spring properties file too 

 - create-schema-tables.sql
    - the script create a schema for the spring datasource and table for the entities 
    - driver table that contains all the driver info and also has FK to the car table  
    - car table that has all car information  
    - user table : used for spring security authentication 
    - role table : used for spring security authorization 
    - user_role composition key with a primary key of user table and role table, many to many relation role and user 

 - predefined-data.sql 
    - has a predefined username and encrypted password set the many to many relations  
 


## client 

we going to use a client `postman` or any other client you prefer for sending HTTP methods

`note before sending any HTTP method` you'll have to authenticate with the server 

to do that choose basic authentication in your client 

and input login and password that predefined in the predefined-data.sql

username : `mohamed` - password: `mohamed` ->  has role of `user` 
username : `ghareeb` - password: `ghareeb` ->  has role of `admin` 

# POST METHOD  

`localhost:8080/add`

**NOTE** only user with role admin can use POST/PUT METHODS - OR ACCESS THESE URL `add` - `remove` - `update`

1- chose post method in the client 
2- in the `json-example` file open `POST` then copy the payload of `post-car-driver` into the client body 
    - to post the car and the driver 
    **note** when you post the car and the driver together, the driver will have an id of the car in the body 
3- click send and use get method for cars and driver  - localhost/8080/cars , localhost:8080/drivers

- or you post only car or driver alone, and to do that:
    - for car POST, copy the payload of the car into the client body from `post-car.json`
    - for driver POST, copy the payload of the driver into the client body from `post-driver.json`

**NOTE**
for driver post only you have to specify the car id - the car only has one driver 

# remove the driver from a car  

`localhost:8080/remove/{driverID}`
`localhost:8080/remove/1`

* This method is a PUT METHOD - it just set the remove the car from the driver object to be allowed to be used by another driver 


# PUT METHOD 

`localhost:8080/update/{carId}`

OR

`localhost:8080/update/{driverId}`

example 

`localhost:8080/update/1`

then in the body, you have to specify either car or driver object 

payload example 

open `json-examples` folder then open `PUT` folder then copy `put-car.json` or `put-driver.json` into the client body 

after use either driver or car entity to update 

you can define whatever properties you want 

like chose to update all the car properties entity or only one 

for the driver entity, you have to check that the car don't have any driver before updating 



**REMEMBER TO AUTHENTICATE WITH ROLE ADMIN BEFORE USING PUT OR REMOVE OR POST METHODS**

# DELETE METHOD 

 **DELETE CAR URL** : `localhost:8080/cars/1`

 delete car api : first it search for the car driver and sent its insatnce to null then delete the car from the DB 

 **DELETE DRIVER URL** : `localhost:8080/drivers/1`

 delete driver api : delete the car driver for the first request becouse driver table has Car fk 

 it set the car driver to null 

 second : delete the driver object 


## THE PROJECT HAS 

* **configuration** for exposing id 
* **spring security** config file to determine the datasource and match url with user role 
* **Rest Controller**  Request Methods for HTTP POST/PUT
* **JPA Data Access Layer** 
* **Data Transfer Layer** to POST/PUT payload 
* **Entities** Car/driver for main program - role/user for security 
* **User Details** for Spring Security to determine the authrization and Authentication and in out case the userDetail Manager takes its information from JPA 
* **service layer** define the logic behind the POST/PUT requests.  
