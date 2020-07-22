package io.github.gmy.sp.loctrack.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDO {
    private int accountId;
    private String name;
    private String pwd;
    private String phone;
}