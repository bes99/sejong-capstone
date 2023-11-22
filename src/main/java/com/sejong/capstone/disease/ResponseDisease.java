package com.sejong.capstone.disease;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDisease {
    private String discernment;
    private String simpleDescription;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String image;
}
