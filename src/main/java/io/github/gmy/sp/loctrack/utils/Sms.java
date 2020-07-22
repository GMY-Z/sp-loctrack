package io.github.gmy.sp.loctrack.utils;

/*
 * 短信发送.
 *
 * @author xhx
 * @since 0.0.1-SNAPSHOT
 */
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.sms.v20190711.SmsClient;

import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.util.Arrays;

import io.github.gmy.sp.loctrack.properties.Properties;

public class Sms {
    private final static String request = "{\"PhoneNumberSet\":[\"+86%s\"],\"TemplateID\":\"" +
            Properties.Sms.templateId +
            "\",\"Sign\":\"" +
            Properties.Sms.sign +
            "\",\"TemplateParamSet\":[\"" +
            "%s" +
            "\"],\"SmsSdkAppid\":\"" +
            Properties.Sms.smsSdkAppId +
            "\"}";

    // 发送短信给目标手机号，不检查是否发送成功
    public static String send(String phone, String description) {
        try{
            Credential cred = new Credential(Properties.Sms.secretId, Properties.Sms.secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, Properties.Sms.region, clientProfile);

            SendSmsRequest req = SendSmsRequest.fromJsonString(String.format(request, phone, description), SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            return Arrays.toString(resp.getSendStatusSet());
        } catch (TencentCloudSDKException e) {
            return "";
        }
    }
}