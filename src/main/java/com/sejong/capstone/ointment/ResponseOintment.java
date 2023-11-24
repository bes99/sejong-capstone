package com.sejong.capstone.ointment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ResponseOintment {
    private String name;
    private Integer price;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;
    private String image;
}
