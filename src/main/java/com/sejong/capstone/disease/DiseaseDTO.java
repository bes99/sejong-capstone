package com.sejong.capstone.disease;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class DiseaseDTO {
    @Schema(description = "질병명", example = "아토피")
    private String discernment;
    @Schema(description = "질병 사진", example = "https://ap-northeast-2.console.aws.amazon.com/")
    private String image;
    @Schema(description = "질병 상세", example = "string")
    private String description;
}