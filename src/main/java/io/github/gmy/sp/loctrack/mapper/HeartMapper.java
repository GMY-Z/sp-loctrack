package io.github.gmy.sp.loctrack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import io.github.gmy.sp.loctrack.DO.HeartDO;

@Mapper
@Repository
public interface HeartMapper {
    HeartDO queryLastHeartByPhone(String phone);
    int addHeart(HeartDO heartDO);
}
