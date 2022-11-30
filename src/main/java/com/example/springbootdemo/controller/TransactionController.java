package com.example.springbootdemo.controller;

import com.example.springbootdemo.dto.*;
import com.example.springbootdemo.model.CustomerModel;
import com.example.springbootdemo.model.DessertModel;
import com.example.springbootdemo.model.OrderModel;
import com.example.springbootdemo.service.iface.ITransactionService;
import com.example.springbootdemo.vo.ResponseHelper;
import com.example.springbootdemo.vo.ResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/transaction")

public class TransactionController {

    @Resource
    ITransactionService iTransactionService;


    @PostMapping("/queryCustomerById")
    public CustomerNewDto queryCustomerById(@RequestBody CustomerDto customerDto) {
        return iTransactionService.queryCustomerById(customerDto);

    }

    @PostMapping("/queryDessertById")
    public DessertModel queryDessertById(@RequestBody DessertDto dessertDto) {
        return iTransactionService.queryDessertById(dessertDto);

    }

    @PostMapping("/queryCustomers")
    public List<CustomerModel> queryCustomer() {
        return iTransactionService.queryCustomer();

    }

    @PostMapping("/queryOrders")
    public List<OrderModel> queryOrders() throws JsonProcessingException {
        return iTransactionService.queryOrders();

    }

    @PostMapping("/processOrder")
    public ResponseVO<Object> processOrder(@RequestBody OrderDto orderDto) {
        return ResponseHelper.success(iTransactionService.processOrder(orderDto));
    }

    @PostMapping("/updateBalance")
    public ResponseVO<Object> updateBalance(@RequestBody WalletUpdateBalanceDto walletUpdateBalanceDto) {
        iTransactionService.updateBalance(walletUpdateBalanceDto);
        return ResponseHelper.success("Success");
    }

    @PostMapping("/deleteOrder")
    public ResponseVO<Object> deleteOrder(@RequestBody OrderDto orderDto) {
        iTransactionService.deleteOrder(orderDto);
        return ResponseHelper.success("Order has been deleted");
    }

    @PostMapping("/addCustomer")
    public ResponseVO<Object> addCustomer(@RequestBody CustomerDto customerDto) {
        iTransactionService.addCustomer(customerDto);
        return ResponseHelper.success("Successfully added new customer! ");
    }

    @PostMapping("/updateOrderDetails")
    public ResponseVO<Object> updateOrderDetails(@RequestBody OrderDto orderDto) {
        iTransactionService.updateOrderDetails(orderDto);
        return ResponseHelper.success("Success");
    }
}
