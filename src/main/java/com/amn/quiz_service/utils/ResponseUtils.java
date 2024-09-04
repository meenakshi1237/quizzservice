package com.amn.quiz_service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    private static ApiResponse APIResponse(){
        return new ApiResponse();
    }

    public static ResponseEntity<ApiResponse> getCreatedResponse(Object response){
        ApiResponse apiResponse=APIResponse();
        apiResponse.setResponse(response);
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    };

    public static ResponseEntity<ApiResponse> getOkResponse(Object object){
        ApiResponse response=APIResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setHttpStatus(HttpStatus.OK);
        response.setResponse(object);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    public static ResponseEntity<ApiResponse> getBadRequestResponse(Object response) {
        ApiResponse apiResponse = APIResponse();
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> getQuestionServiceDown(Object response) {
        ApiResponse apiResponse = APIResponse();
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setResponse(response);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }


}
