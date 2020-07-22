package io.github.gmy.sp.loctrack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.gmy.sp.loctrack.DTO.HeartDTO;
import io.github.gmy.sp.loctrack.service.HeartService;
import io.github.gmy.sp.loctrack.service.StepsService;


@SpringBootTest
class XhxTests {

    private final HeartService heartService;
    private final StepsService stepsService;

    @Autowired
    XhxTests(HeartService heartService, StepsService stepsService) {
        this.heartService = heartService;
        this.stepsService = stepsService;
    }

    @Test
    public void heart() {
        System.out.println(heartService.addHeartByDeviceId(new HeartDTO(65, 0.2), 1));
        System.out.println(heartService.addHeartByDeviceId(new HeartDTO(66, 0.18), 1));
        System.out.println(heartService.addHeartByDeviceId(new HeartDTO(67, 0.15), 1));
        System.out.println(heartService.queryLastHeartByPhone("18768194932"));
    }

    @Test
    public void steps() {
        System.out.println(stepsService.setSteps(1, 90));
        System.out.println(stepsService.queryStepsByPhone("18768194932"));
    }
}
