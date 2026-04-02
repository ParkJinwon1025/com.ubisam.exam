package com.ubisam.exam.api.trains;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam.domain.Train;

@Component
@RepositoryEventHandler
public class TrainHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(Train e) throws Exception {
        // 로그 코드 작성
        // 테스트시에는 System.out.println() 사용/ 실무에서는 LOG 사용
        System.out.println("[HandleBeforeCreate]" + e);
    }

    @HandleAfterCreate
    public void afterCreate(Train a) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterCreate]" + a);

    }

    @HandleBeforeSave
    public void beforeSave(Train a) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeSave]" + a);

    }

    @HandleAfterSave
    public void afterSave(Train a) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + a);

    }

    @HandleBeforeDelete
    public void beforeDelete(Train a) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeDelete]" + a);

    }

    @HandleAfterDelete
    public void afterDelete(Train a) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + a);

    }

}
