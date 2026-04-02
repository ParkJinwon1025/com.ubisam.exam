package com.ubisam.exam.api.trainRoutes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.TrainRoute;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class TrainRouteDocs extends MockMvcRestDocs {

    public TrainRoute newEntity(String... entity) {
        TrainRoute trainRoute = new TrainRoute();
        trainRoute.setTrainRouteName(entity.length > 0 ? entity[0] : super.randomText("trainRouteName"));
        trainRoute.setTrainRouteStart(entity.length > 1 ? entity[1] : super.randomText("trainRouteStart"));
        trainRoute.setTrainRouteEnd(entity.length > 2 ? entity[2] : super.randomText("trainRouteEnd"));
        return trainRoute;

    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String trainRouteName) {
        entity.put("trainRouteName", trainRouteName);
        return entity;
    }

    public Map<String, Object> setSearchName(String name) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchName", name);
        return entity;
    }

}
