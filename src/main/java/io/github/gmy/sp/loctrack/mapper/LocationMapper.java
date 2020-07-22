package io.github.gmy.sp.loctrack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

import io.github.gmy.sp.loctrack.DO.LocationDO;


@Mapper
@Repository
public interface LocationMapper {
    List<LocationDO> queryLocationsByPhone(String phone);
    LocationDO queryLastLocationByPhone(String phone);
    int addLocation(LocationDO locationDO);
}
