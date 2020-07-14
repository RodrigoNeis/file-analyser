package com.agibank.test.fileanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Sale extends BaseData {
    private String saleId;
    private List<SaleItem> saleItemList;
    private String salesManName;

    @Builder
    public Sale(String id, String saleId, List<SaleItem> saleItemList, String salesManName) {
        super(id);
        this.saleId = saleId;
        this.saleItemList = saleItemList;
        this.salesManName = salesManName;
    }

    public double getTotalValueOfSale() {
        return saleItemList.stream()
                .map(saleItem -> saleItem.getPrice() * saleItem.getQuantity())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}
