package io.github.gmy.sp.loctrack.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDO {
    private int deviceId;
    private double longitude;
    private double latitude;
    private long timestamp;
}