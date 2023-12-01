package com.sejong.capstone.user;

import com.sejong.capstone.error.BaseResponse;
import com.sejong.capstone.error.DataResponse;
import com.sejong.capstone.ointment.ResponseOintment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/")
    public BaseResponse join(@Valid @RequestBody UserFormDTO userFormDTO){
        userService.join(userFormDTO);
        return new BaseResponse();
    }
    @GetMapping("/{id}")
    public DataResponse<User> select(@PathVariable Long id){
        return new DataResponse<>(userService.findById(id));
    }
    @PutMapping("/{id}")
    public BaseResponse setSexAndBirth(@PathVariable Long id, @RequestParam String sex,
                                       @RequestParam String birth){
        userService.setSexAndBirth(id,sex,birth);
        return new BaseResponse();
    }
    @PostMapping("/home")
    public DataResponse<UserResponse> home(@RequestBody UserLoginDTO userLoginDTO){
        return new DataResponse<>(userService.home(userLoginDTO.getEmail()));
    }
    @GetMapping("/ointments/{id}")
    public DataResponse<List<ResponseOintment>> getOintmentsForUser(@PathVariable Long id){
        return new DataResponse<>(userService.getOintmentsForUser(id));
    }
    @DeleteMapping("/{id}")
    public BaseResponse deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return new BaseResponse();
    }
}
