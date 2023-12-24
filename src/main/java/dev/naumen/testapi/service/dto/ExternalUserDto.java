package dev.naumen.testapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalUserDto {

    private String login;

    private String fullName;

    private String phoneNumber;

    private String email;

    private LocalDate birthDay;

}
