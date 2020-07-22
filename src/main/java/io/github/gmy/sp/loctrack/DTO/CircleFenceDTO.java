package io.github.gmy.sp.loctrack.DTO;

import lombok.Data;

/**
 * @authon GMY
 * @create 2020-06-12 23:22
 */
@Data
public class CircleFenceDTO {
    String name;
    String center;
    double radius;
    String enable = "true";
    String repeat = "Mon,Tues,Wed,Thur,Fri,Sat,Sun";
    String alert_condition = "enter,leave";
}
