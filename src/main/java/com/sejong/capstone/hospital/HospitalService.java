package com.sejong.capstone.hospital;

import com.sejong.capstone.error.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public List<ResponseHospitalList> viewHospitalList(RequestCoordinate requestCoordinate){
        List<Hospital> hospitals = hospitalRepository.findAll();

        if (hospitals == null || hospitals.isEmpty()) {
            throw new NoNearHospitalFoundException(MessageUtils.NO_SEARCH_NEAR_HOSPITAL);
        }

        List<ResponseHospitalList> sortedHospitalList = hospitals.stream()
                .sorted(Comparator.comparingDouble(hospital ->
                        calculateDistance(hospital.getLatitude(), hospital.getLongitude(),
                                requestCoordinate.getLatitude(), requestCoordinate.getLongitude())))
                .map(hospital -> ResponseHospitalList.builder()
                        .id(hospital.getId())
                        .name(hospital.getName())
                        .address(hospital.getAddress())
                        .roughMap(hospital.getRoughMap())
                        .tel(hospital.getTel())
                        .image(hospital.getImage())
                        .build())
                .collect(Collectors.toList());

        return sortedHospitalList;
    }

    /**
     * Haversine 공식을 사용하여 두 지점 간의 거리를 계산할 수 있다. 이 공식은 지구의 모양을 고려하여 두 지점 간의 거리를 계산한다.
     */
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km

        return distance;
    }
}
