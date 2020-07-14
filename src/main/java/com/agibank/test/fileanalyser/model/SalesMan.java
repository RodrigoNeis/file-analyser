package com.agibank.test.fileanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SalesMan extends BaseData {
    private String cpf;
    private String name;
    private double salary;

    @Builder
    public SalesMan(String id, String cpf, String name, double salary) {
        super(id);
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }
}
