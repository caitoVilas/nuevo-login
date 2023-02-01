package com.caito.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {

    private Integer code;
    private LocalDateTime timestamp;
    private String message;
    private String path;
}
