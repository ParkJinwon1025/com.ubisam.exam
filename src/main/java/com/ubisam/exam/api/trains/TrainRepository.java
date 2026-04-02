package com.ubisam.exam.api.trains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ubisam.exam.domain.Train;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long>, JpaSpecificationExecutor<Train> {

    List<Train> findByTrainCode(String trainCode);

    List<Train> findByTrainTotalSeats(Integer trainTotalSeats);

    List<Train> findByTrainStatus(String trainStatus);

}
