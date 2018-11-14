package com.dinry.clouddisk.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * ModelApiResponse
 */
@Validated
@Data
public class ApiResponse implements Serializable {
    @JsonProperty("returnCode")
    private Integer returnCode = null;
    @JsonProperty("returnType")
    private String returnType = null;
    @JsonProperty("returnData")
    private Object returnData = null;

    public ApiResponse() {
        super();
    }

    public static ResponseEntity<ApiResponse> response(int returnCode, String returnMsg, Object returnData) {
        ApiResponse res = new ApiResponse();
        res.setReturnCode(returnCode);
        res.setReturnType(returnMsg);
        res.setReturnData(returnData);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> falseResponse(int returnCode, String returnMsg, Object returnData) {
        ApiResponse res = new ApiResponse();
        res.setReturnCode(returnCode);
        res.setReturnType(returnMsg);
        res.setReturnData(returnData);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ApiResponse> reuploadResponse(int returnCode, String returnMsg, Object returnData) {
        ApiResponse res = new ApiResponse();
        res.setReturnCode(returnCode);
        res.setReturnType(returnMsg);
        res.setReturnData(returnData);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<ApiResponse> successResponse(Object returnData) {
        return response(200, "success", returnData);
    }

    public static ResponseEntity<ApiResponse> fileNotFoundResponse(String returnMsg) {
        return reuploadResponse(204, returnMsg, null);
    }

    public static ResponseEntity<ApiResponse> validResponse(String returnMsg) {
        return falseResponse(500, returnMsg, null);
    }

    public static ResponseEntity<ApiResponse> errorResponse(String returnMsg) {
        return response(400, returnMsg, null);
    }


}
