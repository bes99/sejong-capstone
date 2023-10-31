package com.sejong.capstone.ointment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class OintmentDTO {
    @Schema(description = "연고 이름", example = "후시딘")
    private String name;
    @Schema(description = "연고 설명", example = "가려울 때 바른다.")
    private String description;
    @Schema(description = "연고 가격", example = "15,000")
    private Integer price;
    @Schema(description = "연고 상세", example = "string")
    private String details;
    @Schema(description = "이미지 링크", example = "https://ap-northeast-2.console.aws.amazon.com/")
    private String image;
}
