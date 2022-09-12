package com.miempresa.cuenta.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
public class TransactionDto {

    @Min(0)
    private double value;

    @NotBlank
    private String description;

    @NotBlank
    private LocalDate date;

    public TransactionDto() {
    }

    public TransactionDto(double value, String description, LocalDate date) {
        this.value = value;
        this.description = description;
        this.date = date;
    }
}
