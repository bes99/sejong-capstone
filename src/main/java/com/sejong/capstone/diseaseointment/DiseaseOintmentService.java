package com.sejong.capstone.diseaseointment;

import com.sejong.capstone.disease.Disease;
import com.sejong.capstone.disease.DiseaseRepository;
import com.sejong.capstone.error.InvalidInputException;
import com.sejong.capstone.error.MessageUtils;
import com.sejong.capstone.ointment.Ointment;
import com.sejong.capstone.ointment.OintmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiseaseOintmentService {
    private final DiseaseOintmentRepository diseaseOintmentRepository;
    private final DiseaseRepository diseaseRepository;
    private final OintmentRepository ointmentRepository;
    public void createDiseaseOintment(Long diseaseId, Long ointmentId){
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_DISEASE_ID));
        Ointment ointment = ointmentRepository.findById(ointmentId)
                .orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_OINTMENT_ID));

        DiseaseOintment diseaseOintment = DiseaseOintment.builder()
                .disease(disease)
                .ointment(ointment)
                .build();

        diseaseOintmentRepository.save(diseaseOintment);
    }
}
