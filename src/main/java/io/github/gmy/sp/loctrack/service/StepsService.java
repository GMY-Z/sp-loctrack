package io.github.gmy.sp.loctrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gmy.sp.loctrack.mapper.StepsMapper;

@Service
public class StepsService {
    private final StepsMapper stepsMapper;

    @Autowired
    public StepsService(StepsMapper stepsMapper) {
        this.stepsMapper = stepsMapper;
    }

    public int queryStepsByPhone(String phone) {
        return stepsMapper.queryStepsByPhone(phone).getSteps();
    }

    public int incStepsByDeviceId(int deviceId) {
        return stepsMapper.incStepsByDeviceId(deviceId);
    }

    public int setSteps(int deviceId, int steps) {
        return stepsMapper.setSteps(deviceId, steps);
    }
}
