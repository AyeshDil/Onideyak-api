package com.myproject.onideyak.onideyakapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponseDTO {
    private int code;
    private String message;
    private Object data;
}
