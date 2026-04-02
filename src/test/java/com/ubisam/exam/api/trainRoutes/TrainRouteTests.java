package com.ubisam.exam.api.trainRoutes;

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

import com.ubisam.exam.domain.TrainRoute;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainRouteTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainRouteDocs docs;

    @Autowired
    private TrainRouteRepository trainRouteRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // Create
        mockMvc.perform(post("/api/trainRoutes").content(docs::newEntity, "1번노선")).andExpect(is2xx())
                .andDo(result(docs::context, "trainRouteEntity"));

        // Read
        String uri = docs.context("trainRouteEntity", "$._links.self.href");
        mockMvc.perform(post(uri)).andExpect(is2xx());

        // // Update
        Map<String, Object> entity = docs.context("trainRouteEntity", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity,
                "11번노선")).andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(uri)).andExpect(is2xx());

    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {
        List<TrainRoute> result;
        boolean hasResult;

        // 40명의 노선 생성
        List<TrainRoute> trainRoutes = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainRoutes.add(docs.newEntity(i + "번노선", "시작역" + i, "종착역" + i));
        }
        trainRouteRepository.saveAll(trainRoutes);

        // trainRouteName 쿼리
        JpaSpecificationBuilder<TrainRoute> query = JpaSpecificationBuilder.of(TrainRoute.class);
        query.where().and().eq("trainRouteName", "10번노선");
        result = trainRouteRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "10번노선".equals(u.getTrainRouteName()));
        assertEquals(true, hasResult);

        // trainRouteStart 쿼리
        JpaSpecificationBuilder<TrainRoute> query1 = JpaSpecificationBuilder.of(TrainRoute.class);
        query1.where().and().eq("trainRouteStart", "시작역30");
        result = trainRouteRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "시작역30".equals(u.getTrainRouteStart()));
        assertEquals(true, hasResult);

        // trainRouteEnt 쿼리
        JpaSpecificationBuilder<TrainRoute> query2 = JpaSpecificationBuilder.of(TrainRoute.class);
        query2.where().and().eq("trainRouteEnd", "종착역21");
        result = trainRouteRepository.findAll(query2.build());
        hasResult = result.stream().anyMatch(u -> "종착역21".equals(u.getTrainRouteEnd()));
        assertEquals(true, hasResult);

    }

    // Search
    @Test
    public void contextLoads2() throws Exception {
        // 40명의 노선 생성
        List<TrainRoute> trainRoutes = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainRoutes.add(docs.newEntity(i + "번노선", "시작역" + i, "종착역" + i));
        }
        trainRouteRepository.saveAll(trainRoutes);

        String uri = "/api/trainRoutes/search";

        // 1. 노선 이름으로 Search
        mockMvc.perform(post(uri).content(docs::setSearchName, "2번노선")).andExpect(is2xx());

        // 2. 페이지네이션 - 4개씩 10페이지
        mockMvc.perform(post(uri).param("size", "4")).andExpect(is2xx()).andDo(print());

        // 3. 정렬 - trainRouteName,desc
        mockMvc.perform(post(uri).param("sort", "trainRouteName,desc")).andExpect(is2xx()).andDo(print());

    }

}
