package com.sejong.capstone.disease;

import com.sejong.capstone.error.BaseResponse;
import com.sejong.capstone.error.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disease")
public class DiseaseController {
    private final DiseaseService diseaseService;
    @PostMapping("/")
    public BaseResponse resistDisease(@RequestBody DiseaseDTO diseaseDTO){
        diseaseService.resistDisease(diseaseDTO);
        return new BaseResponse();
    }
    @GetMapping("/{id}")
    public DataResponse<Disease> getDisease(@PathVariable Long id){
        return new DataResponse<>(diseaseService.getDisease(id));
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteDisease(@PathVariable Long id){
        diseaseService.deleteDisease(id);
        return new BaseResponse();
    }
}
