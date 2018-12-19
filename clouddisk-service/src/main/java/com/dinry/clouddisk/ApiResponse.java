package com.dinry.clouddisk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * ModelApiResponse
 *
 * @author 吴佳杰
 */
@Validated
@Data
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = -8407728322598034531L;

    @JsonProperty("returnCode")
    private Integer returnCode = null;
    @JsonProperty("returnType")
    private String returnType = null;
    @JsonProperty("returnData")
    private Object returnData = null;

    private ApiResponse() {
        super();
    }

    public static ResponseEntity<ApiResponse> response(int returnCode, String returnMsg, Object returnData) {
        ApiResponse res = new ApiResponse();
        res.setReturnCode(returnCode);
        res.setReturnType(returnMsg);
        res.setReturnData(returnData);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.valueOf(returnCode));
    }

    private static ResponseEntity<ApiResponse> commonResponse(int returnCode, String returnMsg, Object returnData) {
        ApiResponse res = new ApiResponse();
        res.setReturnCode(returnCode);
        res.setReturnType(returnMsg);
        res.setReturnData(returnData);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> successResponse(Object returnData) {
        return response(200, "success", returnData);
    }

    public static ResponseEntity<ApiResponse> fileNotFoundResponse(String returnMsg, Object returnData) {
        return response(204, returnMsg, returnData);
    }

    public static ResponseEntity<ApiResponse> validResponse(String returnMsg) {
        return response(500, returnMsg, null);
    }

    public static ResponseEntity<ApiResponse> errorResponse(String returnMsg) {
        return commonResponse(400, "error", returnMsg);
    }

}
