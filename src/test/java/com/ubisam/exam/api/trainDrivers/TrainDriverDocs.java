package com.ubisam.exam.api.trainDrivers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.TrainDriver;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class TrainDriverDocs extends MockMvcRestDocs {

    public TrainDriver newEntity(String... entity) {
        TrainDriver trainDriver = new TrainDriver();
        trainDriver.setTrainDriverName(entity.length > 0 ? entity[0] : super.randomText("trainDriverName"));
        trainDriver.setTrainDriverLicenseNumber(
                entity.length > 1 ? entity[1] : super.randomText("trainDriverLicenseNumber"));
        trainDriver.setTrainDriverCareerYears(entity.length > 2 ? Integer.valueOf(entity[2]) : super.randomInt());
        return trainDriver;

    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String trainDriverName) {
        entity.put("trainDriverName", trainDriverName);
        return entity;
    }

    public Map<String, Object> setSearchName(String name) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchName", name);
        return entity;
    }

    public Map<String, Object> setsearchLicenseNumber(String LicenseNumber) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchLicenseNumber", LicenseNumber);
        return entity;
    }

}
