package com.sejong.capstone.disease;

import com.sejong.capstone.config.S3Service;
import com.sejong.capstone.error.MethodUtils;
import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import com.sejong.capstone.ointment.Ointment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final S3Service s3Service;
    @Transactional
    public void resistDisease(DiseaseDTO diseaseDTO) throws IOException{

        File file = MethodUtils.convertMultipartFileToFile(diseaseDTO.getImage());
        String fileName = UUID.randomUUID().toString() + ".jpg";
        s3Service.uploadFile(fileName, file);

        Disease disease = Disease.builder()
                .discernment(diseaseDTO.getDiscernment())
                .image(s3Service.getFileUrl(fileName))
                .description1(diseaseDTO.getDescription1())
                .description2(diseaseDTO.getDescription2())
                .description3(diseaseDTO.getDescription3())
                .description4(diseaseDTO.getDescription4())
                .build();
        diseaseRepository.save(disease);
    }

    public ResponseDisease getDisease(Long id){
        Disease disease = diseaseRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_DISEASE_ID));

        ResponseDisease responseDisease = ResponseDisease.builder()
                .discernment(disease.getDiscernment())
                .simpleDescription(disease.getSimpleDescription())
                .description1(disease.getDescription1())
                .description2(disease.getDescription2())
                .description3(disease.getDescription3())
                .description4(disease.getDescription4())
                .image(disease.getImage())
                .build();
        return responseDisease;
    }

    @Transactional
    public void deleteDisease(Long id){
        if (!diseaseRepository.existsById(id)) {
            throw new InvalidInputException(MessageUtils.INVALID_DISEASE_ID);
        }
        diseaseRepository.deleteById(id);
    }
}
