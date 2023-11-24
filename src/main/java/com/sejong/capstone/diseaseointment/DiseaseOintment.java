package com.sejong.capstone.diseaseointment;

import com.sejong.capstone.disease.Disease;
import com.sejong.capstone.ointment.Ointment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "disease_ointment_tb")
public class DiseaseOintment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "ointment_id")
    private Ointment ointment;

}
