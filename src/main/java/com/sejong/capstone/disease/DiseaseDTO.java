package com.sejong.capstone.disease;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema
@Builder
public class DiseaseDTO {
    @Schema(description = "질병명", example = "아토피")
    private String discernment;
    @Schema(description = "질병 간단 설명", example = "string")
    private String simpleDescription;
    @Schema(description = "질병 상세 1", example = "string")
    private String description1;
    @Schema(description = "질병 상세 2", example = "string")
    private String description2;
    @Schema(description = "질병 상세 3", example = "string")
    private String description3;
    @Schema(description = "질병 상세 4", example = "string")
    private String description4;
    @Schema(description = "이미지 파일", example = "example.jpg")
    private MultipartFile image;

}
