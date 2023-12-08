package com.sejong.capstone.ointment;

import com.sejong.capstone.error.BaseResponse;
import com.sejong.capstone.error.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ointment")
public class OintmentController {
    private final OintmentService ointmentService;
    @PostMapping("/")
    public BaseResponse resistDisease(@ModelAttribute OintmentDTO ointmentDTO) throws IOException {
        ointmentService.resistOintment(ointmentDTO);
        return new BaseResponse();
    }
    @GetMapping("/{id}")
    public DataResponse<ResponseOintment> getDisease(@PathVariable Long id){
        return new DataResponse<>(ointmentService.getOintment(id));
    }
    @GetMapping
    public DataResponse<List<ResponseOintment>> getOintmentList(){
        return new DataResponse<>(ointmentService.getOintmentList());
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteDisease(@PathVariable Long id){
        ointmentService.deleteOintment(id);
        return new BaseResponse();
    }
}
