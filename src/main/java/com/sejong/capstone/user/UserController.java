package com.sejong.capstone.user;

import com.sejong.capstone.error.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> select(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
