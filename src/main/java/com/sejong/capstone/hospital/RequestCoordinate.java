package com.sejong.capstone.hospital;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class RequestCoordinate {
    @Schema(description = "Double", defaultValue = "37.5128173828125")
    private Double latitude;
    @Schema(description = "Double", defaultValue = "127.01065826416016")
    private Double longitude;
}
