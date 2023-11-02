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
        if(userFormDTO.getPassword() != userFormDTO.getCheckPassword()){
            throw new InvalidInputException(MessageUtils.MISMATCH_PASSWORD);
        }
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

    public User findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        return user;
    }
    @Transactional
    public void setSexAndBirth(Long id, String sex, String birth){
        userRepository.setUserSexAndBirth(id,sex,birth);
    }
    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        userRepository.delete(user);
    }
}
