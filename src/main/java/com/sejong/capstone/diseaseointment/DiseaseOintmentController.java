package com.sejong.capstone.diseaseointment;

import com.sejong.capstone.error.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disease-ointment")
public class DiseaseOintmentController {
    private final DiseaseOintmentService diseaseOintmentService;
    @PostMapping("/")
    public BaseResponse resistDiseaseOintment(@RequestParam Long diseaseId,
                                              @RequestParam Long ointmentId){
        diseaseOintmentService.createDiseaseOintment(diseaseId,ointmentId);
        return new BaseResponse();
    }
}
