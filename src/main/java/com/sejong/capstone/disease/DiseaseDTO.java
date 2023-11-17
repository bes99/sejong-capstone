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
    @Schema(description = "질병 상세 1", example = "string")
    private String description1;
    @Schema(description = "질병 상세 2", example = "string")
    private String description2;
    @Schema(description = "질병 상세 3", example = "string")
    private String description3;
    @Schema(description = "질병 상세 4", example = "string")
    private String description4;
}
