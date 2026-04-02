package com.ubisam.exam.api.trainStations;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import com.ubisam.exam.domain.TrainStation;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class TrainStationHandler {

    @HandleBeforeRead
    public void handleBeforeRead(TrainStation e, Specification<TrainStation> spec) throws Exception {
        // 로그 코드 작성
        // 테스트에서는 System.out.println() 사용 / 실무에서는 LOG 사용
        System.out.println("[HandleBeforeRead]" + e);
        JpaSpecificationBuilder<TrainStation> query = JpaSpecificationBuilder.of(TrainStation.class);
        query.where()
                .and().eq("trainStationName", e.getSearchName())
                .and().eq("trainStationCode", e.getSearchCode()).build(spec);

    }

    @HandleAfterRead
    public void HandleAfterRead(TrainStation e, Serializable r) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeRead]" + e);
    }

    @HandleBeforeCreate
    public void HandleBeforeCreate(TrainStation e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeCreate]" + e);
    }

    @HandleAfterCreate
    public void HandleAfterCreate(TrainStation e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleAfterCreate]" + e);
    }

    @HandleBeforeSave
    public void HandleBeforeSave(TrainStation e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeSave]" + e);
    }

    @HandleAfterSave
    public void HandleAfterSave(TrainStation e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleAfterSave]" + e);
    }

    @HandleBeforeDelete
    public void HandleBeforeDelete(TrainStation e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeDelete]" + e);
    }

    @HandleAfterDelete
    public void HandleAfterDelete(TrainStation e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleAfterDelete]" + e);
    }

}
