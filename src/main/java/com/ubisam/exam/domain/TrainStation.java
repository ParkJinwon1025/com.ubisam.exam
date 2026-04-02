package com.ubisam.exam.domain;

// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "example_train_station")
public class TrainStation {

    @Id
    @GeneratedValue
    private Long id;

    // 정류장 이름
    private String trainStationName;

    // 정류장 코드
    private String trainStationCode;

    // 하나의 정류장에는 여러 개의 노선이 존재
    // @ManyToMany(mappedBy = "trainStations")
    // private List<TrainRoute> trainRoutes;

    @Transient
    private String searchName;

    @Transient
    private String searchCode;

}
