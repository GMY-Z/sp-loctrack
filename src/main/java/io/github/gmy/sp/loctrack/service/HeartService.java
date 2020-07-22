package io.github.gmy.sp.loctrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gmy.sp.loctrack.DO.HeartDO;
import io.github.gmy.sp.loctrack.DO.LocationDO;
import io.github.gmy.sp.loctrack.DTO.GpsDTO;
import io.github.gmy.sp.loctrack.DTO.HeartDTO;
import io.github.gmy.sp.loctrack.mapper.HeartMapper;
import io.github.gmy.sp.loctrack.mapper.LocationMapper;

@Service
public class HeartService {
    private final HeartMapper heartMapper;

    @Autowired
    public HeartService(HeartMapper heartMapper) {
        this.heartMapper = heartMapper;
    }

    public HeartDTO queryLastHeartByPhone(String phone) {
        return convertHeartDOToHeartDTO(heartMapper.queryLastHeartByPhone(phone));
    }

    public int addHeartByDeviceId(HeartDTO heartDTO, int deviceId) {
        HeartDO heartDO = convertHeartDTOToHeartDO(
                deviceId,
                heartDTO,
                System.currentTimeMillis() / 1000
        );
        return heartMapper.addHeart(heartDO);
    }

    private HeartDTO convertHeartDOToHeartDTO(HeartDO heartDO) {
        return new HeartDTO(heartDO.getRate(), heartDO.getOxy());
    }

    private HeartDO convertHeartDTOToHeartDO(int deviceId, HeartDTO heartDTO, long timestamp) {
        return new HeartDO(
                deviceId,
                heartDTO.getRate(),
                heartDTO.getOxy(),
                timestamp);
    }
}
