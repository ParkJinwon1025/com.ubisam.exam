package com.ubisam.exam.api.trains;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Train;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class TrainDocs extends MockMvcRestDocs {

    public Train newEntity(String... entity) {
        Train train = new Train();
        train.setTrainCode(entity.length > 0 ? entity[0] : super.randomText("trainCode"));
        train.setTrainTotalSeats(entity.length > 1 ? Integer.valueOf(entity[1]) : super.randomInt());
        train.setTrainStatus(entity.length > 2 ? entity[2] : super.randomText("trainStatus"));
        return train;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String trainCode) {
        entity.put("trainCode", trainCode);
        return entity;
    }

}
