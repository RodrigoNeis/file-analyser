package com.agibank.test.fileanalyser.processor;

import com.agibank.test.fileanalyser.model.BaseData;
import com.agibank.test.fileanalyser.model.Client;
import com.agibank.test.fileanalyser.model.Sale;
import com.agibank.test.fileanalyser.model.SaleItem;
import com.agibank.test.fileanalyser.model.SalesMan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataProcessor {
    private static final String SALESMAN_ID = "001";
    private static final String CLIENT_ID = "002";
    private static final String SALE_ID = "003";

    public BaseData getObject(String line) {
        String[] columns = line.split("ç");
        String id = "";

        if (columns.length >= 1) {
            id = columns[0];
        }
        switch (id) {
            case SALESMAN_ID:
                return buildSalesMan(columns);
            case CLIENT_ID:
                return buildClient(columns);
            case SALE_ID:
                return buildSale(columns);
            default:
                return null;
        }
    }

    private BaseData buildSalesMan(String[] columns) {
        return SalesMan.builder()
                .id(columns[0])
                .cpf(columns[1])
                .name(columns[2])
                .salary(Double.valueOf(columns[3]))
                .build();
    }

    private BaseData buildClient(String[] columns) {
        return Client
                .builder()
                .id(columns[0])
                .cnpj(columns[1])
                .name(columns[2])
                .businessArea(columns[3])
                .build();
    }

    private BaseData buildSale(String[] columns) {
        return Sale.builder()
                .id(columns[0])
                .saleId(columns[1])
                .saleItemList(buildItems(columns[2]))
                .salesManName(columns[3])
                .build();
    }

    private List<SaleItem> buildItems(String column) {
        String itemsString = column.substring(column.indexOf("[") + 1, column.indexOf("]"));
        String itemsColumns[] = itemsString.split(",");

        List<SaleItem> saleItemsObj = new ArrayList<SaleItem>();
        Arrays.stream(itemsColumns).forEach(item -> {
            String itemValues[] = item.split("-");
            saleItemsObj.add(SaleItem.builder()
                    .itemId(itemValues[0])
                    .quantity(Integer.valueOf(itemValues[1]))
                    .price(Double.valueOf(itemValues[2]))
                    .build());
        });
        return saleItemsObj;
    }
}
