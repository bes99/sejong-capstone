package com.sejong.capstone.error;

import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse{
    private T data;

    public DataResponse(T data) {
        super();
        this.data = data;
    }
    public DataResponse(T data, Integer status) {
        super("", status);
        this.data = data;
    }

    public DataResponse(String reason, Integer status, T data) {
        super(reason, status);
        this.data = data;
    }
}
