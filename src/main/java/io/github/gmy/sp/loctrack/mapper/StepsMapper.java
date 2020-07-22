package io.github.gmy.sp.loctrack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import io.github.gmy.sp.loctrack.DO.StepsDO;

@Mapper
@Repository
public interface StepsMapper {
    StepsDO queryStepsByPhone(String phone);
    int incStepsByDeviceId(int deviceId);
    int setSteps(int deviceId, int steps);
}
