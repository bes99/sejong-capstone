package com.sejong.capstone.user;

import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserFormDTO userFormDTO){
        if(userRepository.existsByEmail(userFormDTO.getEmail())){
            throw new InvalidInputException(MessageUtils.DUPLICATE_USER_EMAIL);
        }
        String encryptedPassword = passwordEncoder.encode(userFormDTO.getPassword());

        User user = User.builder()
                .name(userFormDTO.getName())
                .email(userFormDTO.getEmail())
                .password(encryptedPassword)
                .build();

        userRepository.save(user);
    }

    public void select(Long id){

    }

}
