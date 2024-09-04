package com.amn.quiz_service.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiResponse {
    HttpStatus httpStatus;
    int statusCode;
    Object response;

}
