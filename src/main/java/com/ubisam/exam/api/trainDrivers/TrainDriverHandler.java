package com.ubisam.exam.api.trainDrivers;

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

import com.ubisam.exam.domain.TrainDriver;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class TrainDriverHandler {

    @HandleBeforeRead
    public void handleBeforeRead(TrainDriver e, Specification<TrainDriver> spec) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeRead]" + e);
        JpaSpecificationBuilder<TrainDriver> query = JpaSpecificationBuilder.of(TrainDriver.class);
        query.where()
                .and().eq("trainDriverName", e.getSearchName())
                .and().eq("trainDriverLicenseNumber", e.getSearchLicenseNumber()).build(spec);
    }

    @HandleAfterRead
    public void HandleAfterRead(TrainDriver e, Serializable r) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeRead]" + e);
    }

    @HandleBeforeCreate
    public void HandleBeforeCreate(TrainDriver e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeCreate]" + e);
    }

    @HandleAfterCreate
    public void HandleAfterCreate(TrainDriver e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleAfterCreate]" + e);
    }

    @HandleBeforeSave
    public void HandleBeforeSave(TrainDriver e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeSave]" + e);
    }

    @HandleAfterSave
    public void HandleAfterSave(TrainDriver e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleAfterSave]" + e);
    }

    @HandleBeforeDelete
    public void HandleBeforeDelete(TrainDriver e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeDelete]" + e);
    }

    @HandleAfterDelete
    public void HandleAfterDelete(TrainDriver e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleAfterDelete]" + e);
    }

}
