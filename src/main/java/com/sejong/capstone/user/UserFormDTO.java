package com.sejong.capstone.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@Schema(description = "회원가입 정보")
public class UserFormDTO {
    @NotBlank(message = "이름 입력은 필수입니다.")
    @Schema(description = "string", defaultValue = "박창현")
    private String name;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Schema(description = "string", defaultValue = "example@naver.com")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Schema(description = "string", defaultValue = "example12!")
    private String password;
    @NotBlank(message = "비밀번호 확인 입력은 필수입니다.")
    @Schema(description = "string", defaultValue = "example12!")
    private String checkPassword;
}
