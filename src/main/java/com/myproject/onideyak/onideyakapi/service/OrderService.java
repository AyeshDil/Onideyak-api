package com.myproject.onideyak.onideyakapi.service;

import com.myproject.onideyak.onideyakapi.dto.request.OrderRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;

public interface OrderService {
    CommonResponseDTO createNewOrder(OrderRequestDTO orderRequestDTO);

    CommonResponseDTO cancelOrder(String orderId);

    CommonResponseDTO getOrders(String state, int page, int size);
}
