package com.miempresa.cuenta.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Getter @Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private double value;
    private String description;
    private LocalDate date;

    public Transaction() {
    }

    public Transaction(double value, String description, LocalDate date) {
        this.value = value;
        this.description = description;
        this.date = date;
    }
}
