package io.github.gmy.sp.loctrack.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepsDO {
    private int deviceId;
    private int steps;
}