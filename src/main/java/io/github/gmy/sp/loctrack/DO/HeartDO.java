package io.github.gmy.sp.loctrack.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartDO {
    private int deviceId;
    private int rate;
    private double oxy;
    private long timestamp;
}