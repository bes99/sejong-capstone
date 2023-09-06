package com.sejong.capstone.disease;

import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import com.sejong.capstone.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;
    @Transactional
    public void resistDisease(DiseaseDTO diseaseDTO){
        Disease disease = Disease.builder()
                .discernment(diseaseDTO.getDiscernment())
                .image(diseaseDTO.getImage())
                .description(diseaseDTO.getDescription())
                .build();
        diseaseRepository.save(disease);
    }

    public Disease getDisease(Long id){
        Disease disease = diseaseRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_DISEASE_ID));
        return disease;
    }

    @Transactional
    public void deleteDisease(Long id){
        if (!diseaseRepository.existsById(id)) {
            throw new InvalidInputException(MessageUtils.INVALID_DISEASE_ID);
        }
        diseaseRepository.deleteById(id);
    }
}
