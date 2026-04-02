package com.ubisam.exam.api.trainStations;

import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam.domain.TrainStation;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainStationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainStationDocs docs;

    @Autowired
    private TrainStationRepository trainStationRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {
        // Create
        mockMvc.perform(post("/api/trainStations").content(docs::newEntity, "1번역")).andExpect(is2xx())
                .andDo(result(docs::context, "trainStationEntity"));

        // Read
        String uri = docs.context("trainStationEntity", "$._links.self.href");
        mockMvc.perform(post(uri)).andExpect(is2xx());

        // // // Update
        Map<String, Object> entity = docs.context("trainStationEntity", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity,
                "12번역")).andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(uri)).andExpect(is2xx());
    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {
        List<TrainStation> result;
        boolean hasResult;

        // 40명의 역 생성
        List<TrainStation> trainStations = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainStations.add(docs.newEntity(i + "번역", "stationCode" + i));
        }
        trainStationRepository.saveAll(trainStations);

        // trainStationName 쿼리
        JpaSpecificationBuilder<TrainStation> query = JpaSpecificationBuilder.of(TrainStation.class);
        query.where().and().eq("trainStationName", "10번역");
        result = trainStationRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "10번역".equals(u.getTrainStationName()));
        assertEquals(true, hasResult);

        // trainStationCode 쿼리
        JpaSpecificationBuilder<TrainStation> query1 = JpaSpecificationBuilder.of(TrainStation.class);
        query1.where().and().eq("trainStationCode", "stationCode20");
        result = trainStationRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "stationCode20".equals(u.getTrainStationCode()));
        assertEquals(true, hasResult);

    }

    // Search
    @Test
    public void contextLoads2() throws Exception {
        // 40명의 역 생성
        List<TrainStation> trainStations = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainStations.add(docs.newEntity(i + "번역", "stationCode" + i));
        }
        trainStationRepository.saveAll(trainStations);

        String uri = "/api/trainStations/search";

        // 1. 역 이름으로 Search
        mockMvc.perform(post(uri).content(docs::setSearchName, "30번역")).andExpect(is2xx());

        // 2. 역 코드로 Search
        mockMvc.perform(post(uri).content(docs::setSearchCode, "stationCode22")).andExpect(is2xx());

        // 3. 페이지네이션 - 10개씩 4페이지
        mockMvc.perform(post(uri).param("size", "10")).andExpect(is2xx()).andDo(print());

        // 4. 정렬 - trainStationName,desc
        mockMvc.perform(post(uri).param("sort", "trainStationName,desc")).andExpect(is2xx()).andDo(print());
    }
}
