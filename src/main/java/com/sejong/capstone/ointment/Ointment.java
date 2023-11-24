package com.sejong.capstone.ointment;

import com.sejong.capstone.disease.Disease;
import com.sejong.capstone.diseaseointment.DiseaseOintment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "ointment_tb")
public class Ointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;
    private String image;
    @OneToMany(mappedBy = "ointment")
    private List<DiseaseOintment> diseases;

}
