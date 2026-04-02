package com.ubisam.exam.api.trainStations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.TrainStation;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class TrainStationDocs extends MockMvcRestDocs {

    public TrainStation newEntity(String... entity) {
        TrainStation trainStation = new TrainStation();
        trainStation.setTrainStationName(entity.length > 0 ? entity[0] : super.randomText("trainStationName"));
        trainStation.setTrainStationCode(entity.length > 1 ? entity[1] : super.randomText("trainStationCode"));
        return trainStation;

    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String trainStationName) {
        entity.put("trainStationName", trainStationName);
        return entity;
    }

    public Map<String, Object> setSearchName(String name) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchName", name);
        return entity;
    }

    public Map<String, Object> setSearchCode(String code) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchCode", code);
        return entity;
    }
}
