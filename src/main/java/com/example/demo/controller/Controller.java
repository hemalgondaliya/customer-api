package com.example.demo.controller;

import com.example.demo.modal.*;
import com.example.demo.repository.*;
import com.example.demo.view.*;
import io.swagger.annotations.ApiOperation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

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
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private ProductModelRepository modelRepository;

    @Autowired
    private BrandRepositiry brandRepositiry;

    @Autowired
    private PaymentRepository paymentRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");

    @RequestMapping(value = "/customer/list", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get customer list", response = Iterable.class)
    public ResponseEntity getCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        List<ShowCustomerVO> result = customers.stream()
                .map(c -> new ShowCustomerVO(c,paymentRepository.findByCustomer_Id(c.getId())))
                .collect(Collectors.toList());
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
        if(!customerRepository.getByBillNumberAndYear(customerVO.getBillNumber(),
                Integer.parseInt(simpleDateFormat.format(customerVO.getDate()))).isEmpty()) {
            return new ResponseEntity<>(new Response("Duplicate customer entry", 404),
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
        return new ResponseEntity(new Response("Customer saved", 200), HttpStatus.OK);
    }

    @RequestMapping(value = "/deliveryPerson/getAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get Deliver person list", response = Iterable.class)
    public ResponseEntity getDeliveryPerson() {
        List<DeliveryPerson> deliveryPeople = deliveryPersonRepository.findAll();
        List<String> result =
                deliveryPeople.stream().map(c -> c.getName()).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/model/all", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Get all available Product list", response = Iterable.class)
    public ResponseEntity getAllAvailableProduct() {
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

    @RequestMapping(value = "/app/properties", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Application properties", response = Iterable.class)
    public ResponseEntity addPayment() {

        ApplicationPropertiesVO applicationPropertiesVO = new ApplicationPropertiesVO();

        return new ResponseEntity<>(applicationPropertiesVO, HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add payment for customer", response = Iterable.class)
    public ResponseEntity addPayment(@RequestBody PaymentVO paymentVO) {

        List<Customer> result = customerRepository.getByBillNumberAndYear(paymentVO.getBillNumber(), paymentVO.getYear());
        if (result.size() == 0) {
            return new ResponseEntity<>(new Response("No customer found for the given input", 400), HttpStatus.BAD_REQUEST);
        }
        if (paymentVO.getCustomerName().equals(result.get(0).getFirstName())) {
            Payment p = new Payment();
            p.setAmount(paymentVO.getAmount());
            p.setCustomer(result.get(0));
            p.setDate(new Date());
            p.setReceiver(paymentVO.getReceiver());
            p.setPaymentMethod(paymentVO.getPaymentMethod());
            if(paymentVO.getChequeNumber() != null) {
                ChequePayment cq = new ChequePayment();
                cq.setChequeNumber(paymentVO.getChequeNumber());
                cq.setBankName(paymentVO.getBankName());
                p.setChequePayment(cq);
            }

            paymentRepository.save(p);
            return new ResponseEntity<>(new Response("Payment is added", 200), HttpStatus.OK);

        }

        return new ResponseEntity<>(new Response("Error", 400), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/customer/find", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add payment for customer", response = Iterable.class)
    public ResponseEntity addPayment(@RequestBody SearchCustomerForPaymentVO searchVO) {

        if (searchVO.getBillNumber() == null || searchVO.getYear() == 0) {
            return new ResponseEntity<>(new Response("Please provide valid data", 400), HttpStatus.BAD_REQUEST);
        }
        List<Customer> result = customerRepository.getByBillNumberAndYear(searchVO.getBillNumber(), searchVO.getYear());
        if (result.size() > 1) {
            return new ResponseEntity<>(new Response("Found more that one customer", 400), HttpStatus.BAD_REQUEST);
        } else if (result.size() == 0) {
            return new ResponseEntity<>(new Response("No customer found for the given input", 400), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CustomerVO(result.get(0)), HttpStatus.OK);
    }

}

