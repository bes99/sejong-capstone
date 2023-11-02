package com.sejong.capstone.user;

import com.sejong.capstone.error.BaseResponse;
import com.sejong.capstone.error.DataResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{id}")
    public BaseResponse deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return new BaseResponse();
    }
}
