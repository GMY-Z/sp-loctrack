package io.github.gmy.sp.loctrack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import io.github.gmy.sp.loctrack.DO.FenceDO;


@Mapper
@Repository
public interface FenceMapper {
    List<FenceDO> queryFenceByPhone(String phone);
    int addFence(List<FenceDO> fence);
    int deleteFenceByPhone(String phone);
}
