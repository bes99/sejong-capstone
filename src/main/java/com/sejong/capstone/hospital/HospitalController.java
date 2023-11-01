package com.sejong.capstone.hospital;

import com.sejong.capstone.error.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/")
    public DataResponse<List<ResponseHospitalList>> viewHospitalList(@RequestBody RequestCoordinate requestCoordinate){
        return new DataResponse<>(hospitalService.viewHospitalList(requestCoordinate));
    }
}
