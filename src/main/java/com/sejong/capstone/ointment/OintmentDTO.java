package com.sejong.capstone.ointment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema
public class OintmentDTO {
    @Schema(description = "연고 이름", example = "후시딘")
    private String name;
    @Schema(description = "연고 가격", example = "15,000")
    private Integer price;
    @Schema(description = "연고 설명 1", example = "가려울 때 바른다.")
    private String description1;
    @Schema(description = "연고 설명 2", example = "가려울 때 바른다.")
    private String description2;
    @Schema(description = "연고 설명 3", example = "가려울 때 바른다.")
    private String description3;
    @Schema(description = "연고 설명 4", example = "가려울 때 바른다.")
    private String description4;
    @Schema(description = "연고 설명 5", example = "가려울 때 바른다.")
    private String description5;
    @Schema(description = "연고 사진", example = "example.jpg")
    private MultipartFile image;
}
