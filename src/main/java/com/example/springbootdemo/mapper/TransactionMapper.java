package com.example.springbootdemo.mapper;

import com.example.springbootdemo.dto.*;
import com.example.springbootdemo.model.CustomerModel;
import com.example.springbootdemo.model.DessertModel;
import com.example.springbootdemo.model.OrderModel;
import com.example.springbootdemo.model.WalletModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TransactionMapper {

    CustomerModel queryCustomerById(CustomerDto customerDto);

    DessertModel queryDessertById (DessertDto dessertDto);



    Integer queryBalance(int walletId);

    List<CustomerModel> queryCustomer();

    List<OrderModel> queryOrders();

    int queryDessertId(String dessertName);

    int queryDessertPrice(int dessertId);

    void insertOrder(OrderModel orderModel);

    void updateWallet(@Param("totalPrice") int totalPrice, @Param("customerId") int customerId);

    void updateOrder(OrderModel orderModel);

    void updateBalance(WalletUpdateBalanceDto walletUpdateBalanceDto);

    void deleteOrder(OrderDto orderDto);

    int addWallet(WalletModel walletModel);

    void addCustomer(CustomerModel customerModel);

    void updateTotalPrice(OrderModel orderModel);

    int  queryTotalPrice(OrderDto orderDto);

    void updateOrderDetails(OrderModel orderModel);



}
