package io.github.gmy.sp.loctrack.service;

import io.github.gmy.sp.loctrack.DO.LocationDO;
import io.github.gmy.sp.loctrack.DTO.GpsDTO;
import io.github.gmy.sp.loctrack.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GpsService {

    private final LocationMapper locationMapper;

    @Autowired
    public GpsService(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    public List<GpsDTO> queryLocationsByPhone(String phone) {
        return convertLocationDOListToGpsDTOList(locationMapper.queryLocationsByPhone(phone));
    }

    public GpsDTO queryLastLocationByPhone(String phone) {
        return convertLocationDOToGpsDTO(locationMapper.queryLastLocationByPhone(phone));
    }

    public int addLocationByDeviceId(GpsDTO gpsDTO, int deviceId) {
        LocationDO locationDO = convertGpsDTOToLocationDO(
                deviceId,
                gpsDTO,
                System.currentTimeMillis() / 1000
        );
        return locationMapper.addLocation(locationDO);
    }

    private List<GpsDTO> convertLocationDOListToGpsDTOList(List<LocationDO> locationDOList) {
        List<GpsDTO> gpsDTOList = new ArrayList<GpsDTO>();
        for (LocationDO locationDO : locationDOList) {
            gpsDTOList.add(convertLocationDOToGpsDTO(locationDO));
        }

        return gpsDTOList;
    }

    private GpsDTO convertLocationDOToGpsDTO(LocationDO locationDO) {
        return new GpsDTO(locationDO.getLongitude(), locationDO.getLatitude());
    }

    private LocationDO convertGpsDTOToLocationDO(int deviceId, GpsDTO gpsDTO, long timestamp) {
        return new LocationDO(
                deviceId,
                gpsDTO.getLongitude(),
                gpsDTO.getLatitude(),
                timestamp);
    }

}
