package com.ubisam.exam.api.trains;

import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.delete;
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

import com.ubisam.exam.domain.Train;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainDocs docs;

    @Autowired
    private TrainRepository trainRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // Create
        mockMvc.perform(post("/api/trains").content(docs::newEntity, "trainCode1")).andExpect(is2xx())
                .andDo(result(docs::context, "trainEntity"));

        // Read
        String uri = docs.context("trainEntity", "$._links.self.href");
        mockMvc.perform(get(uri)).andExpect(is2xx());

        // Update
        Map<String, Object> entity = docs.context("trainEntity", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity, "trainCode11")).andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(uri)).andExpect(is2xx());
    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {
        List<Train> result;
        boolean hasResuslt;

        // 기차 40개 등록
        List<Train> trains = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trains.add(docs.newEntity("trainCode" + i, i + "", "trainStatus" + i));
        }
        trainRepository.saveAll(trains);

        // trainCode 쿼리
        JpaSpecificationBuilder<Train> query = JpaSpecificationBuilder.of(Train.class);
        query.where().and().eq("trainCode", "trainCode3");
        result = trainRepository.findAll(query.build());
        hasResuslt = result.stream().anyMatch(u -> "trainCode3".equals(u.getTrainCode()));
        assertEquals(true, hasResuslt);

        // trainTotalSeats 쿼리
        JpaSpecificationBuilder<Train> query1 = JpaSpecificationBuilder.of(Train.class);
        query1.where().and().eq("trainTotalSeats", 30);
        result = trainRepository.findAll(query1.build());
        hasResuslt = result.stream().anyMatch(u -> 30 == u.getTrainTotalSeats());
        assertEquals(true, hasResuslt);

        // trainStatus 쿼리
        JpaSpecificationBuilder<Train> query2 = JpaSpecificationBuilder.of(Train.class);
        query2.where().and().eq("trainStatus", "trainStatus29");
        result = trainRepository.findAll(query2.build());
        hasResuslt = result.stream().anyMatch(u -> "trainStatus29".equals(u.getTrainStatus()));
        assertEquals(true, hasResuslt);
    }

    // Search
    @Test
    public void contextLoads2() throws Exception {
        // 기차 40개 등록
        List<Train> trains = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trains.add(docs.newEntity("trainCode" + i, i + "", "trainStatus" + i));
        }
        trainRepository.saveAll(trains);

        // 1.trainCode Search
        mockMvc.perform(get("/api/trains/search/findByTrainCode").param("trainCode", "trainCode3")).andExpect(is2xx());

        // 2.trainTotalSeats Search
        mockMvc.perform(get("/api/trains/search/findByTrainTotalSeats").param("trainTotalSeats", "20"))
                .andExpect(is2xx());

        // 3.trainStatus Search
        mockMvc.perform(get("/api/trains/search/findByTrainStatus").param("trainStatus", "trainStatus39"))
                .andExpect(is2xx());

        // 4. 페이지네이션 - 8개씩 5페이지
        mockMvc.perform(get("/api/trains").param("size", "8")).andDo(print())
                .andExpect(is2xx());

        // 5. 정렬 - trainCode,desc
        mockMvc.perform(get("/api/trains").param("sort", "trainCode,desc")).andDo(print())
                .andExpect(is2xx());
    }

}
