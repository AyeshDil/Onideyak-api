package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.dto.request.OrderRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Orders;
import com.myproject.onideyak.onideyakapi.entity.User;
import com.myproject.onideyak.onideyakapi.enums.OrderState;
import com.myproject.onideyak.onideyakapi.exception.ConflictException;
import com.myproject.onideyak.onideyakapi.exception.NotFoundException;
import com.myproject.onideyak.onideyakapi.repo.OrdersRepo;
import com.myproject.onideyak.onideyakapi.repo.UserRepo;
import com.myproject.onideyak.onideyakapi.service.OrderService;
import com.myproject.onideyak.onideyakapi.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    private final OrdersRepo ordersRepo;
    private final Generator generator;
    private final UserRepo userRepo;

    @Autowired
    public OrderServiceIMPL(OrdersRepo ordersRepo, Generator generator, UserRepo userRepo) {
        this.ordersRepo = ordersRepo;
        this.generator = generator;
        this.userRepo = userRepo;
    }

    @Override
    public CommonResponseDTO createNewOrder(OrderRequestDTO orderRequestDTO) {
        // generate order id
        String generatedOrderId = generator.generatedPrimaryKey(generator.generatePrefix(5), "O", 10);
        // get user
        User user = userRepo.getReferenceById(orderRequestDTO.getUserPropertyId());
        // if the order exist in database in same id
        if (ordersRepo.existsById(generatedOrderId)) throw new ConflictException("Try again!");
        // save
        Orders orders = new Orders(
                generatedOrderId,
                new Date(),
                orderRequestDTO.getTotalCost(),
                orderRequestDTO.getShippingAddress(),
                OrderState.PENDING,
                user,
                null,
                null

        );

        ordersRepo.save(orders);
        return new CommonResponseDTO(200, "order created!", generatedOrderId);
    }

    @Override
    public CommonResponseDTO cancelOrder(String orderId) {
        // find the order according to the order id
        Optional<Orders> selectedOrder = ordersRepo.findById(orderId);

        if (selectedOrder.isEmpty())
            throw new NotFoundException("Wrong order id");

        // check whether the order states are rejected or verified
        if (selectedOrder.get().getOrderState().equals(OrderState.REJECTED))
            return new CommonResponseDTO(202, "Already canceled the order", null);

        if (selectedOrder.get().getOrderState().equals(OrderState.VERIFIED))
            return new CommonResponseDTO(403, "Can not cancel the order", null);

        // update the order state tyo the rejected state
        selectedOrder.get().setOrderState(OrderState.REJECTED);
        ordersRepo.save(selectedOrder.get());
        return new CommonResponseDTO(201, "Order canceled!", null);


    }

    @Override
    public CommonResponseDTO getOrders(String state, int page, int size) {
        /*
        * need to implement
        * * */
        return null;
    }
}
