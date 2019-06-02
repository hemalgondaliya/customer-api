package com.example.demo.controller;

import com.example.demo.modal.*;
import com.example.demo.repository.*;
import com.example.demo.service.CustomerService;
import com.example.demo.service.DeliveryPersonService;
import com.example.demo.service.ModelService;
import com.example.demo.service.PaymentService;
import com.example.demo.VO.*;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DeliveryPersonService deliveryPersonService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/customer/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get customer list", response = Iterable.class)
    public ResponseEntity getAllCustomer() {
        List<ShowCustomerVO> result = customerService.getAllCustomer();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Save customer", response = Iterable.class)
    public ResponseEntity saveCustomer(@RequestBody CustomerVO customerVO) {
        if (customerVO.getDeliveryPerson() == null) {
            return new ResponseEntity<>(new Response("Please choose delivery person", 404),
                    HttpStatus.BAD_REQUEST);
        }

        if (!customerService
                .isValidatedByBillAndYear(customerVO.getBillNumber(), customerVO.getDate())) {
            return new ResponseEntity<>(new Response("Duplicate customer entry", 404),
                    HttpStatus.BAD_REQUEST);
        }

        return customerService.saveCustomer(customerVO);
    }

    @RequestMapping(value = "/customer/deprecate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Set deprecation for given customer", response = Iterable.class)
    public ResponseEntity deprecateCustomer(@RequestBody CustomerIdentityVO customerIdentityVO) {
        return customerService.deprecateCustomer(customerIdentityVO.getBillNumber(), customerIdentityVO.getYear());
    }

    @RequestMapping(value = "/deliveryPerson/getAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get Deliver person list", response = Iterable.class)
    public ResponseEntity getDeliveryPerson() {
        return new ResponseEntity<>(deliveryPersonService.getAllDeliveryPersonName(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/deliveryPerson/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add Deliver person", response = Iterable.class)
    public ResponseEntity addDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        return deliveryPersonService.saveDeliveryPerson(deliveryPerson);
    }

    @RequestMapping(value = "/model/all", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get all available Product list", response = Iterable.class)
    public ResponseEntity getAllAvailableProduct() {
        Map<String, List<AllProductVO>> optimisedResult = modelService.getAllAvailableProduct();
        return new ResponseEntity<>(optimisedResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/model/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get all available Product list", response = Iterable.class)
    public ResponseEntity addNewModel(@RequestBody ModelVO modelVo) {
        return modelService.addNewModel(modelVo);
    }

    @RequestMapping(value = "/model/deprecate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Set deprecation for given model", response = Iterable.class)
    public ResponseEntity deprecateModel(@RequestBody ModelVO modelVO) {
        return modelService.deprecateModel(modelVO);
    }

    @RequestMapping(value = "/app/properties", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Application properties", response = Iterable.class)
    public ResponseEntity getApplicationProperties() {
        ApplicationPropertiesVO applicationPropertiesVO = new ApplicationPropertiesVO();
        return new ResponseEntity<>(applicationPropertiesVO, HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add payment for customer", response = Iterable.class)
    public ResponseEntity addPayment(@RequestBody PaymentVO paymentVO) {

        List<Customer> result = customerService
                .getByBillNumberAndYear(paymentVO.getBillNumber(), paymentVO.getYear());
        if (result.size() == 0 || result.size() > 1) {
            return new ResponseEntity<>(new Response(result.size() + "customer found for the given input", 400),
                    HttpStatus.BAD_REQUEST);
        }
        if(paymentService.isDuplicate(paymentVO, result.get(0))){
            return new ResponseEntity<>(new Response( "Duplicate payment entry", 400),
                    HttpStatus.BAD_REQUEST);
        }

        if(paymentService.isOverPaid(result.get(0),paymentVO.getAmount())) {
            return new ResponseEntity<>(new Response( "You can not pay more than total bill amount", 400),
                    HttpStatus.BAD_REQUEST);
        }
        if (paymentVO.getCustomerName().equals(result.get(0).getFirstName())) {
            return paymentService.save(paymentVO, result.get(0));
        }
        return new ResponseEntity<>(new Response("Error while adding payment", 400), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/customer/find", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add payment for customer", response = Iterable.class)
    public ResponseEntity findCustomerByBillAndYear(@RequestBody CustomerIdentityVO searchVO) {

        if (searchVO.getBillNumber() == null || searchVO.getYear() == 0) {
            return new ResponseEntity<>(new Response("Please provide valid data", 400),
                    HttpStatus.BAD_REQUEST);
        }
        List<Customer> result = customerRepository
                .getByBillNumberAndYear(searchVO.getBillNumber(), searchVO.getYear());
        if (result.size() > 1) {
            return new ResponseEntity<>(new Response("Found more that one customer", 400),
                    HttpStatus.BAD_REQUEST);
        } else if (result.size() == 0) {
            return new ResponseEntity<>(new Response("No customer found for the given input", 400),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomerVO(result.get(0)), HttpStatus.OK);
    }

}

