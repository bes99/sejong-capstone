package com.sejong.capstone.ointment;

import com.sejong.capstone.disease.Disease;
import com.sejong.capstone.disease.DiseaseDTO;
import com.sejong.capstone.disease.DiseaseRepository;
import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OintmentService {
    private final OintmentRepository ointmentRepository;
    @Transactional
    public void resistOintment(OintmentDTO ointmentDTO){
        Ointment ointment = Ointment.builder()
                .name(ointmentDTO.getName())
                .description(ointmentDTO.getDescription())
                .price(ointmentDTO.getPrice())
                .details(ointmentDTO.getDetails())
                .image(ointmentDTO.getImage())
                .build();
        ointmentRepository.save(ointment);
    }

    public Ointment getOintment(Long id){
        Ointment ointment = ointmentRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_OINTMENT_ID));
        return ointment;
    }

    @Transactional
    public void deleteOintment(Long id){
        if (!ointmentRepository.existsById(id)) {
            throw new InvalidInputException(MessageUtils.INVALID_OINTMENT_ID);
        }
        ointmentRepository.deleteById(id);
    }
}
