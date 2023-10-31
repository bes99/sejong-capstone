package com.sejong.capstone.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema
public class BaseResponse {
    @Schema(description = "성공/실패 유무", example = "SUCCESS")
    private String result;
    @Schema(description = "성공하면 공백, 실패 시 원인 반환", example = "")
    private String msg;
    @Schema(description = "HTTP status code", example = "200")
    private Integer status;

    public BaseResponse(){
        this.result = MessageUtils.SUCCESS;
        this.msg  = "";
        this.status = 200;
    }
    public BaseResponse(String reason, Integer status){
        this.result = MessageUtils.FAIL;
        this.msg = reason;
        this.status = status;
    }
}
