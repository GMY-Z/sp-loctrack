package io.github.gmy.sp.loctrack.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GMY
 * @create 2020-05-30 19:36
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpsDTO {
    private double longitude;
    private double latitude;

    @Override
    public String toString() {
        return "[" + longitude + "," + latitude + "]";
    }
}
