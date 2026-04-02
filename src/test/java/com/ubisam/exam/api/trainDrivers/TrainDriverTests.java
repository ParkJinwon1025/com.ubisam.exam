package com.ubisam.exam.api.trainDrivers;

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

import com.ubisam.exam.domain.TrainDriver;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainDriverTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainDriverDocs docs;

    @Autowired
    private TrainDriverRepository trainDriverRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // Create
        mockMvc.perform(post("/api/trainDrivers").content(docs::newEntity, "기관사1")).andExpect(is2xx())
                .andDo(result(docs::context, "trainDriverEntity"));

        // Read
        String uri = docs.context("trainDriverEntity", "$._links.self.href");
        mockMvc.perform(post(uri)).andExpect(is2xx());

        // Update
        Map<String, Object> entity = docs.context("trainDriverEntity", "$");
        mockMvc.perform(put(uri).content(docs::updateEntity, entity, "기관사12")).andExpect(is2xx());

        // Delete
        mockMvc.perform(delete(uri)).andExpect(is2xx());

    }

    // Handler
    @Test
    public void contextLoads1() throws Exception {
        List<TrainDriver> result;
        boolean hasResult;

        // 40명의 기관사 생성
        List<TrainDriver> trainDrivers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainDrivers.add(docs.newEntity(i + "번기관사", "LicenseNumber" + i, i + ""));
        }
        trainDriverRepository.saveAll(trainDrivers);

        // trainDriverName 쿼리
        JpaSpecificationBuilder<TrainDriver> query = JpaSpecificationBuilder.of(TrainDriver.class);
        query.where().and().eq("trainDriverName", "3번기관사");
        result = trainDriverRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "3번기관사".equals(u.getTrainDriverName()));
        assertEquals(true, hasResult);

        // trainDriverLicenseNumber 쿼리
        JpaSpecificationBuilder<TrainDriver> query1 = JpaSpecificationBuilder.of(TrainDriver.class);
        query1.where().and().eq("trainDriverLicenseNumber", "LicenseNumber29");
        result = trainDriverRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "LicenseNumber29".equals(u.getTrainDriverLicenseNumber()));
        assertEquals(true, hasResult);

        // trainDriverCareerYears 쿼리
        JpaSpecificationBuilder<TrainDriver> query2 = JpaSpecificationBuilder.of(TrainDriver.class);
        query2.where().and().eq("trainDriverCareerYears", 12);
        result = trainDriverRepository.findAll(query2.build());
        hasResult = result.stream().anyMatch(u -> 12 == u.getTrainDriverCareerYears());
        assertEquals(true, hasResult);
    }

    // Search
    @Test
    public void contextLoads2() throws Exception {

        // 40명의 기관사 생성
        List<TrainDriver> trainDrivers = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            trainDrivers.add(docs.newEntity(i + "번기관사", "LicenseNumber" + i, i + ""));
        }
        trainDriverRepository.saveAll(trainDrivers);

        // 1. trainDriverName으로 Search
        mockMvc.perform(post("/api/trainDrivers/search").content(docs::setSearchName, "2번기관사")).andExpect(is2xx());

        // 2. trainDriverLicenseNumber로 Search
        mockMvc.perform(post("/api/trainDrivers/search").content(docs::setsearchLicenseNumber, "LicenseNumber30"))
                .andExpect(is2xx());

        // 3. 페이지네이션 : 10개씩 4페이지
        mockMvc.perform(post("/api/trainDrivers/search").param("size", "10")).andDo(print()).andExpect(is2xx());

        // 4. 정렬 : trainDriverName,desc
        mockMvc.perform(post("/api/trainDrivers/search").param("sort", "trainDriverName,desc")).andDo(print())
                .andExpect(is2xx());
    }

}
