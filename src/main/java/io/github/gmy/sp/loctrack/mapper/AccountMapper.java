package io.github.gmy.sp.loctrack.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface AccountMapper {
    Integer authenticate(String phone, String pwd);
    Integer queryDeviceIdByPhone(String phone);
    String queryDeviceDescriptionByPhone(String phone);
}
