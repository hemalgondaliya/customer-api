package com.example.demo.controller;

import io.swagger.annotations.ApiOperation;

import java.sql.SQLException;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Customer;
import com.example.demo.modal.DeliveryPerson;
import com.example.demo.modal.Response;
import com.example.demo.modal.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DeliveryPersonRepository;
import com.example.demo.service.AuthService;
import com.example.demo.view.CustomerVO;

@RestController
@RequestMapping("/user")
public class Controller {

    private AuthService authService;

	@Autowired
    private UserRepository baseRepository;

	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public Controller(AuthService authService) {
        this.authService = authService;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get user login",response = Iterable.class)
    public ResponseEntity<String> getLogin(@RequestBody(required = true) User user)
            throws SQLException {
        User result = baseRepository.findUsersByName(user.getName());
        authService.authenticateUser(user);
        if (result.getName().equals(user.getName()) && result.getPassword()
                .equals(user.getPassword())) {
               return new ResponseEntity(new Response("Login Success",200),HttpStatus.OK);
           }

        return new ResponseEntity(new Response("Login Fail!",401), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/customer/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get customer list",response = Iterable.class)
    public ResponseEntity getCustomerList()
            throws SQLException {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerVO> result = customers.stream().map(CustomerVO::new).collect(
                Collectors.toList());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get customer list",response = Iterable.class)
    public ResponseEntity saveCustomer(@RequestBody(required = true) CustomerVO customerVO)
            throws SQLException {
        Customer customer = customerVO.getCustomer();
        customer.setDeliveryPerson(deliveryPersonRepository.findByName(customerVO.getDeliveryPerson()));
        customerRepository.save(customer);
        return new ResponseEntity(new Response("Customer saved",200), HttpStatus.OK);
    }

    @RequestMapping(value = "/deliveryPerson/getAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get Deliver person list",response = Iterable.class)
    public ResponseEntity getDeliveryPerson()
            throws SQLException {
        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();
        List<String> result =
                deliveryPeople.stream().map(c -> c.getName()).collect(Collectors.toList());
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
