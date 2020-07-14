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
public class Client extends BaseData {
    private String cnpj;
    private String name;
    private String businessArea;

    @Builder
    public Client(String id, String cnpj, String name, String businessArea) {
        super(id);
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }
}
