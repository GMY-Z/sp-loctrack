package io.github.gmy.sp.loctrack.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author GMY
 * @create 2020-05-30 19:36
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HeartDTO {
    private int rate;
    private double oxy;
}
