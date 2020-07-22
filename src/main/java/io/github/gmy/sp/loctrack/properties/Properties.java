package io.github.gmy.sp.loctrack.properties;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Properties {
    public static final class Sms {
        public static final boolean allowSms = true;

        public static final String sign = "奚浩轩编程学习测试网";
        public static final String secretId = "AKIDXtzqopx2HAQQ9bzOM3n57tPb3JI0OigO";
        public static final String secretKey = "KuDvSmT0BAtLWzWKvFIqnSr06wVrw85r";
        public static final String region = "ap-shanghai";
        public static final String templateId = "634061";
        public static final String smsSdkAppId = "1400382646";

        public static final Set<String> acceptedPhones = new HashSet<String>(Arrays.asList(
                "13957195170", "17326025559", "18768194932"
        ));
    }
}
