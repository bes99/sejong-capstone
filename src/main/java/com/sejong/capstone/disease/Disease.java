package com.sejong.capstone.disease;

import com.sejong.capstone.diseaseointment.DiseaseOintment;
import com.sejong.capstone.ointment.Ointment;
import com.sejong.capstone.userdisease.UserDisease;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "disease_tb")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String discernment;
    @Column(name = "simple_description")
    private String simpleDescription;
    private String image;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    @OneToMany(mappedBy = "disease")
    private List<UserDisease> userDiseases;
    @OneToMany(mappedBy = "disease")
    private List<DiseaseOintment> ointments;

}
