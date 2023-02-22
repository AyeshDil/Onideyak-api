package com.myproject.onideyak.onideyakapi.controller;

import com.myproject.onideyak.onideyakapi.dto.request.OrderRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.service.OrderService;
import com.myproject.onideyak.onideyakapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = {"/visitor/new-order"})
    public ResponseEntity<StandardResponse> createNewOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        CommonResponseDTO commonResponseDTO = orderService.createNewOrder(orderRequestDTO);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.CREATED
        );
    }

    @PutMapping(value = {"/member/cancel/{orderId}"})
    public ResponseEntity<StandardResponse> cancelOrder(@PathVariable(value = "orderId") String orderId) {
        CommonResponseDTO commonResponseDTO = orderService.cancelOrder(orderId);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );
    }

    @GetMapping(value = {"/member/get"},
            params = {"state", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getOrders(
            @RequestParam(value = "state", defaultValue = "all") String state,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){

        CommonResponseDTO commonResponseDTO = orderService.getOrders(state, page, size);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );
    }

}
