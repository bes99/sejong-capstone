package com.sejong.capstone.ointment;

import com.sejong.capstone.error.BaseResponse;
import com.sejong.capstone.error.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ointment")
public class OintmentController {
    private final OintmentService ointmentService;
    @PostMapping("/")
    public BaseResponse resistDisease(@RequestBody OintmentDTO ointmentDTO){
        ointmentService.resistOintment(ointmentDTO);
        return new BaseResponse();
    }
    @GetMapping("/{id}")
    public DataResponse<Ointment> getDisease(@PathVariable Long id){
        return new DataResponse<>(ointmentService.getOintment(id));
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteDisease(@PathVariable Long id){
        ointmentService.deleteOintment(id);
        return new BaseResponse();
    }
}
