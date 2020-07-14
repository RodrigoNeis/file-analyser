package com.agibank.test.fileanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SaleItem {
    private String itemId;
    private int quantity;
    private double price;
}
