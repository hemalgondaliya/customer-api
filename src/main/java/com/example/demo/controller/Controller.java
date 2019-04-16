package com.example.demo.controller;

import com.example.demo.modal.*;
import com.example.demo.repository.*;
import com.example.demo.view.AllProductVO;
import com.example.demo.view.ModelVO;
import com.example.demo.view.ProductVO;
import io.swagger.annotations.ApiOperation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AuthService;
import com.example.demo.view.CustomerVO;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private ProductModelRepository modelRepository;

    @Autowired
    private BrandRepositiry brandRepositiry;

    @RequestMapping(value = "/customer/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get customer list", response = Iterable.class)
    public ResponseEntity getCustomerList()
            throws SQLException {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerVO> result = customers.stream().map(CustomerVO::new).collect(
                Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get customer list", response = Iterable.class)
    public ResponseEntity saveCustomer(@RequestBody CustomerVO customerVO)
            throws SQLException {
        if (customerVO.getDeliveryPerson() == null) {
            return new ResponseEntity<>(new Response("Please choose delivery person", 404),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerVO.getCustomer();
        List<Product> products = new ArrayList();
        List<ProductVO> productVOS = customerVO.getSelectedProducts();

        for (ProductVO entry : productVOS) {
            ProductModel model = modelRepository.findByName(entry.getModel());
            Product p = new Product();
            p.setProductModel(model);
            p.setPrice(entry.getPrice());
            p.setSerialNumber(entry.getSerialNumber());
            products.add(p);
        }
        customer.setDeliveryPerson(deliveryPersonRepository.findByName(customerVO.getDeliveryPerson()));
        customer.setProductList(products);
        customerRepository.save(customer);
        return new ResponseEntity<>(new Response("Customer saved", 200), HttpStatus.OK);
    }

    @RequestMapping(value = "/deliveryPerson/getAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get Deliver person list", response = Iterable.class)
    public ResponseEntity getDeliveryPerson()
            throws SQLException {
        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();
        List<String> result =
                deliveryPeople.stream().map(c -> c.getName()).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/model/all", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get all available Product list", response = Iterable.class)
    public ResponseEntity getAllAvailableProduct() throws SQLException {
        List<ProductModel> result = modelRepository.findAll();

        Map<String, List<ProductModel>> finalResult =
                result.stream().filter(model -> !model.isDeprecated()).collect(Collectors
                        .groupingBy(model -> model.getBrand().getName() + "-" + model.getBrand()
                                .getCategory()));

        Map<String, List<AllProductVO>> optimisedResult = finalResult.entrySet().stream().collect(Collectors
                .toMap(Map.Entry::getKey, e -> e.getValue().stream().map(AllProductVO::new).collect(Collectors.toList())));

        return new ResponseEntity<>(optimisedResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/model/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get all available Product list", response = Iterable.class)
    public ResponseEntity addNewModel(@RequestBody ModelVO modelVo) throws SQLException {
        ProductModel m = new ProductModel();
        Brand b = brandRepositiry
                .findByNameAndCategory(modelVo.getBrandName(), modelVo.getCategoryName());
        m.setBrand(b);
        m.setDeprecated(false);
        m.setName(modelVo.getModelName());

        if (modelRepository.findByName(modelVo.getModelName()) != null) {
            return new ResponseEntity<>(new Response("Duplicate model!", 500), HttpStatus.BAD_REQUEST);
        } else {
            modelRepository.save(m);
            return new ResponseEntity<>(new Response("Model saved", 200), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/model/deprecate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Set deprecation for given model", response = Iterable.class)
    public ResponseEntity deprecateModel(@RequestBody ModelVO modelVO) throws SQLException {
        ProductModel m = modelRepository.findByName(modelVO.getModelName());
        if (modelVO.getBrandName().equals(m.getBrand().getName())) {
            m.setDeprecated(true);
            modelRepository.save(m);
        }
        return new ResponseEntity<>(new Response("Model saved", 200), HttpStatus.OK);
    }

}

