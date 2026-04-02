package com.ubisam.exam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "example_train")
public class Train {

    @Id
    @GeneratedValue
    private Long id;

    // 기차 코드
    private String trainCode;

    // 기차 좌석수
    private Integer trainTotalSeats;

    // 기차 상태
    private String trainStatus;

    // 하나의 기차는 하나의 노선만 가질 수 있음.
    // @ManyToOne
    // private TrainRoute trainRoute;

    // 한대의 기차는 하나의 기관사만 운행할 수 있음.
    // @ManyToOne
    // private TrainDriver trainDriver;
}
