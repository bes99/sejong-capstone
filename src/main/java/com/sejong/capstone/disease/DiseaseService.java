package com.sejong.capstone.disease;

import com.sejong.capstone.config.S3Service;
import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import com.sejong.capstone.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final S3Service s3Service;
    @Transactional
    public void resistDisease(DiseaseDTO diseaseDTO) throws IOException{

        File file = convertMultipartFileToFile(diseaseDTO.getImage());
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
    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file);
             InputStream is = multipartFile.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return file;
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
