package io.github.gmy.sp.loctrack.service;

import io.github.gmy.sp.loctrack.DO.FenceDO;
import io.github.gmy.sp.loctrack.DTO.GpsDTO;
import io.github.gmy.sp.loctrack.mapper.AccountMapper;
import io.github.gmy.sp.loctrack.mapper.FenceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FenceService {

    private final FenceMapper fenceMapper;
    private final AccountMapper accountMapper;

    @Autowired
    public FenceService(FenceMapper fenceMapper, AccountMapper accountMapper) {
        this.fenceMapper = fenceMapper;
        this.accountMapper = accountMapper;
    }

    public List<GpsDTO> queryFenceByPhone(String phone) {
        return convertFenceDOListToGpsDTOList(fenceMapper.queryFenceByPhone(phone));
    }

    public int addFenceByPhone(List<GpsDTO> gpsDTOList, String phone) {
        try {
            int deviceId = accountMapper.queryDeviceIdByPhone(phone);

            if (fenceMapper.queryFenceByPhone(phone).size() != 0) {
                throw new Exception();
            }

            return fenceMapper.addFence(convertGpsDTOListToFenceDOList(gpsDTOList,deviceId));
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // no rows changed
        }
    }

    public int deleteFenceByPhone(String phone) {
        return fenceMapper.deleteFenceByPhone(phone);
    }

    private List<GpsDTO> convertFenceDOListToGpsDTOList(List<FenceDO> fenceDOList) {
        List<GpsDTO> gpsDTOList = new ArrayList<GpsDTO>();
        for (FenceDO fenceDO : fenceDOList) {
            gpsDTOList.add(convertFenceDOTOGpsDTO(fenceDO));
        }

        return gpsDTOList;
    }

    private GpsDTO convertFenceDOTOGpsDTO(FenceDO fenceDO) {
        return new GpsDTO(fenceDO.getLongitude(), fenceDO.getLatitude());
    }

    private List<FenceDO> convertGpsDTOListToFenceDOList(List<GpsDTO> gpsDTOList, int deviceId) {
        List<FenceDO> fenceDOList = new ArrayList<FenceDO>();
        int i = 0;
        for (GpsDTO gpsDTO : gpsDTOList) {
            fenceDOList.add(new FenceDO(
                    deviceId,
                    gpsDTO.getLongitude(),
                    gpsDTO.getLatitude(),
                    i++
            ));
        }

        return fenceDOList;
    }
}
