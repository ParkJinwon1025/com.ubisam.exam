package com.ubisam.exam.domain;

// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "example_train_route")
public class TrainRoute {

    @Id
    @GeneratedValue
    private Long id;

    // 노선 이름
    private String trainRouteName;

    // 노선 출발지
    private String trainRouteStart;

    // 노선 종착지
    private String trainRouteEnd;

    // 하나의 노선에는 여러 가지 기차가 존재함.
    // @OneToMany(mappedBy = "trainRoute")
    // private List<Train> trains;

    // 하나의 노선에는 여러 개의 정류장이 배치될 수 있음.
    // @ManyToMany
    // private List<TrainStation> trainStations;

    @Transient
    private String searchName;

}
