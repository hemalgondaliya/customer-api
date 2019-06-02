package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.modal.Customer;
import com.example.demo.modal.Payment;
import com.example.demo.modal.Product;
import com.example.demo.modal.ProductModel;
import com.example.demo.modal.Response;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DeliveryPersonRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductModelRepository;
import com.example.demo.VO.CustomerVO;
import com.example.demo.VO.ProductVO;
import com.example.demo.VO.ShowCustomerVO;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private ProductModelRepository modelRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");

    public ResponseEntity saveCustomer(CustomerVO customerVO) {
        Customer customer = customerVO.getCustomer();
        List<Product> products = new ArrayList<>();
        List<ProductVO> productVOS = customerVO.getSelectedProducts();

        for (ProductVO entry : productVOS) {
            ProductModel model = modelRepository.findByName(entry.getModel());
            Product p = new Product();
            p.setProductModel(model);
            p.setPrice(entry.getPrice());
            p.setSerialNumber(entry.getSerialNumber());
            products.add(p);
        }
        customer.setDeprecated(false);
        customer.setDeliveryPerson(deliveryPersonRepository.findByName(customerVO.getDeliveryPerson()));
        customer.setProductList(products);
        customerRepository.save(customer);
        return new ResponseEntity<>(new Response("Customer saved", 200), HttpStatus.OK);
    }

    public List<ShowCustomerVO> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<ShowCustomerVO> result = customers.stream().filter(c -> !c.isDeprecated() )
                .map(c -> new ShowCustomerVO(c, this.getDebtAmountByCustomer(c)))
                        .collect(Collectors.toList());
        return result;
    }

    public ResponseEntity deprecateCustomer(Integer billNumber, int year) {
        List<Customer> customerList = customerRepository.getByBillNumberAndYear(billNumber, year);

        if (customerList.size() == 0 || customerList.size() > 1) {
            return new ResponseEntity<>(
                    new Response(customerList.size() + "customer found for the given input", 400),
                    HttpStatus.BAD_REQUEST);
        }
        Customer c = customerList.get(0);
        c.setDeprecated(true);
        customerRepository.save(c);
        return new ResponseEntity<>(new Response("Customer is deleted", 200), HttpStatus.OK);
    }

    public List<Customer> getByBillNumberAndYear(int billNumber, int year) {
        return customerRepository.getByBillNumberAndYear(billNumber, year);
    }

    public boolean isValidatedByBillAndYear(Integer billNumber, Date date) {
       return  customerRepository.getByBillNumberAndYear(billNumber,
                Integer.parseInt(simpleDateFormat.format(date))).isEmpty();
    }

    public Integer getDebtAmountByCustomer(Customer customer) {
        return this.getBillAmountByCustomer(customer) - this
                .getTotalPaidAmountById(customer.getId());
    }

    private Integer getTotalPaidAmountById(Long custId) {
        List<Payment> paymentList = paymentRepository.findByCustomer_Id(custId);
        int totalPaid = paymentList.stream().map(p -> p.getAmount())
                .collect(Collectors.summingInt(Integer::intValue));
        return totalPaid;
    }

    private Integer getBillAmountByCustomer(Customer customer) {
        List<Product> productList = customer.getProductList();
        int billAmount = productList.stream().map(p -> p.getPrice())
                .collect(Collectors.summingInt(Integer::intValue));
        return billAmount;
    }
}
