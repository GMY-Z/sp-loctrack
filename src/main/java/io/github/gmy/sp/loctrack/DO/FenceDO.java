package io.github.gmy.sp.loctrack.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FenceDO {
    private int deviceId;
    private double longitude;
    private double latitude;
    private int num;
}