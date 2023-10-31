package com.sejong.capstone.hospital;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "hospital_tb")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private String classification;
    @Column(name = "rough_map")
    private String roughMap;
    @Column(name = "closing_time_weekday")
    private String closingTimeWeekday;
    @Column(name = "closing_time_saturday")
    private String closingTimeSaturday;
    @Column(name = "closing_time_sunday")
    private String closingTimeSunday;
    private String tel;
    private String image;
}
