package io.github.gmy.sp.loctrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gmy.sp.loctrack.mapper.AccountMapper;
import io.github.gmy.sp.loctrack.properties.Properties;
import io.github.gmy.sp.loctrack.utils.Sms;

@Service
public class SmsAlertService {
    private final AccountMapper accountMapper;

    @Autowired
    public SmsAlertService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public boolean send(String phone) {
        // 只允许发送给白名单内手机号
        if (!Properties.Sms.acceptedPhones.contains(phone)) {
            return false;
        }

        // get device description
        String description = accountMapper.queryDeviceDescriptionByPhone(phone);

        // 如果true则实际发送，否则只输出一个结果
        // 假设一定能发送成功
        if (Properties.Sms.allowSms) {
            Sms.send(phone, description);
        }
        return true;
    }
}
