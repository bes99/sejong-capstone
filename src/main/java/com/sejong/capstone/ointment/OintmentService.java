package com.sejong.capstone.ointment;

import com.sejong.capstone.config.S3Service;
import com.sejong.capstone.error.MethodUtils;
import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OintmentService {
    private final OintmentRepository ointmentRepository;
    private final S3Service s3Service;
    @Transactional
    public void resistOintment(OintmentDTO ointmentDTO) throws IOException {
        File file = MethodUtils.convertMultipartFileToFile(ointmentDTO.getImage());
        String fileName = UUID.randomUUID().toString() + ".jpg";
        s3Service.uploadFile(fileName, file);

        Ointment ointment = Ointment.builder()
                .name(ointmentDTO.getName())
                .price(ointmentDTO.getPrice())
                .description1(ointmentDTO.getDescription1())
                .description2(ointmentDTO.getDescription2())
                .description3(ointmentDTO.getDescription3())
                .description4(ointmentDTO.getDescription4())
                .description5(ointmentDTO.getDescription5())
                .image(s3Service.getFileUrl(fileName))
                .build();
        ointmentRepository.save(ointment);
    }

    public ResponseOintment getOintment(Long id){
        Ointment ointment = ointmentRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_OINTMENT_ID));

        ResponseOintment responseOintment = ResponseOintment.builder()
                .name(ointment.getName())
                .price(ointment.getPrice())
                .description1(ointment.getDescription1())
                .description2(ointment.getDescription2())
                .description3(ointment.getDescription3())
                .description4(ointment.getDescription4())
                .description5(ointment.getDescription5())
                .image(ointment.getImage())
                .build();
        return responseOintment;
    }

    @Transactional
    public void deleteOintment(Long id){
        if (!ointmentRepository.existsById(id)) {
            throw new InvalidInputException(MessageUtils.INVALID_OINTMENT_ID);
        }
        ointmentRepository.deleteById(id);
    }
}
