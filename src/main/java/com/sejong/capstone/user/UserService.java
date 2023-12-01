package com.sejong.capstone.user;

import com.sejong.capstone.disease.Disease;
import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import com.sejong.capstone.ointment.Ointment;
import com.sejong.capstone.ointment.ResponseOintment;
import com.sejong.capstone.userdisease.UserDisease;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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
    public UserResponse home(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_EMAIL_ID));

        String diseaseName = "null";
        if (user.getUserDisease() != null && user.getUserDisease().getDisease() != null) {
            diseaseName = user.getUserDisease().getDisease().getDiscernment();
        }
        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .disease(diseaseName)
                .build();
        return userResponse;
    }

    public User findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        return user;

    }
    public List<ResponseOintment> getOintmentsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_USER_ID));

        UserDisease userDisease = user.getUserDisease();
        if (userDisease == null) {
            return Collections.emptyList();
        }

        Disease disease = userDisease.getDisease();
        return disease.getOintments().stream()
                .map(diseaseOintment -> convertToResponseOintment(diseaseOintment.getOintment()))
                .distinct()
                .collect(Collectors.toList());
    }

    private ResponseOintment convertToResponseOintment(Ointment ointment) {
        return ResponseOintment.builder()
                .name(ointment.getName())
                .price(ointment.getPrice())
                .description1(ointment.getDescription1())
                .description2(ointment.getDescription2())
                .description3(ointment.getDescription3())
                .description4(ointment.getDescription4())
                .description5(ointment.getDescription5())
                .image(ointment.getImage())
                .build();
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
