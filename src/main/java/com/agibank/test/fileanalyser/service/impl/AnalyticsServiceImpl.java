package com.agibank.test.fileanalyser.service.impl;

import com.agibank.test.fileanalyser.model.BaseData;
import com.agibank.test.fileanalyser.model.Client;
import com.agibank.test.fileanalyser.model.Sale;
import com.agibank.test.fileanalyser.model.SalesMan;
import com.agibank.test.fileanalyser.service.AnalyticsService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    private List<BaseData> basicDataList;

    @Override
    public void loadData(List<BaseData> basicDataList) {
        this.basicDataList = basicDataList;
    }

    @Override
    public String getAnalyticsResults() {
        StringBuilder outPutData = new StringBuilder("The results of the analytics are:");
        outPutData.append('\n').append("Number of Clients = [" + this.getTotalNumberOfClients() + "]");
        outPutData.append('\n').append("Number of Sales Man = [" + this.getTotalNumberOfSalesMan() + "]");
        outPutData.append('\n').append("ID of the most Expensive sale = [" + this.getIdOfMostExpensiveSale() + "]");
        outPutData.append('\n').append("The worst SalesMan is = [" + this.getWorstSalesMan() + "]");
        return outPutData.toString();
    }

    private int getTotalNumberOfClients() {
        int total = 0;
        for (BaseData baseData : basicDataList) {
            if (baseData instanceof Client) {
                total++;
            }
        }
        return total;
    }

    private int getTotalNumberOfSalesMan() {
        int total = 0;
        for (BaseData baseData : basicDataList) {
            if (baseData instanceof SalesMan) {
                total++;
            }
        }
        return total;
    }

    private String getIdOfMostExpensiveSale() {
        Sale mostExpensiveSale = null;
        for (BaseData baseData : basicDataList) {
            if (baseData instanceof Sale) {
                Sale sale = (Sale) baseData;
                if (mostExpensiveSale == null || mostExpensiveSale.getTotalValueOfSale() < sale.getTotalValueOfSale()) {
                    mostExpensiveSale = sale;
                }
            }
        }
        return mostExpensiveSale.getSaleId();
    }

    private SalesMan getWorstSalesMan() {
        String salesName = null;
        Double value = null;
        for (Map.Entry<String, Double> entry : getHashSalesManNameAndTotalSales().entrySet()) {

            String key = entry.getKey();
            Double mapValue = entry.getValue();
            if (salesName == null && value == null) {
                salesName = key;
                value = mapValue;
            }
            if (value > mapValue) {
                value = mapValue;
                salesName = key;
            }
        }
        return getSalesManByName(salesName);

    }

    private HashMap<String, Double> getHashSalesManNameAndTotalSales() {
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (BaseData basicData : basicDataList) {
            if (basicData instanceof Sale) {
                Sale sale = (Sale) basicData;
                if (map.containsKey(sale.getSalesManName())) {
                    Double value = map.get(sale.getSalesManName());
                    value += sale.getTotalValueOfSale();
                    map.put(sale.getSalesManName(), value);
                } else {
                    map.put(sale.getSalesManName(), sale.getTotalValueOfSale());
                }
            }
        }
        return map;
    }

    private SalesMan getSalesManByName(String name) {
        for (BaseData basicData : basicDataList) {
            if (basicData instanceof SalesMan) {
                SalesMan salesMan = (SalesMan) basicData;
                if (name.equals(salesMan.getName())) {
                    return salesMan;
                }
            }
        }
        return null;
    }
}
