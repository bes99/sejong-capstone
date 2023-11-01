package com.sejong.capstone.hospital;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema
public class ResponseHospitalList {
    private Long id;
    private String name;
    private String address;
    private String roughMap;
    private String tel;
    private String image;
}
