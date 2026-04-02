package com.ubisam.exam.domain;

// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "example_train_driver")
public class TrainDriver {

    @Id
    @GeneratedValue
    private Long id;

    // 기관사 이름
    private String trainDriverName;

    // 기관사 면허 번호
    private String trainDriverLicenseNumber;

    // 기관사 경력
    private Integer trainDriverCareerYears;

    // 하나의 기관사는 여러 개의 기차를 운행할 수 있음.
    // @OneToMany(mappedBy = "trainDriver")
    // private List<Train> trains;

    @Transient
    private String searchName;

    @Transient
    private String searchLicenseNumber;

}
