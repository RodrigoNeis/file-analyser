package com.agibank.test.fileanalyser.service;

import com.agibank.test.fileanalyser.model.BaseData;
import com.agibank.test.fileanalyser.model.SalesMan;

import java.util.List;

public interface AnalyticsService {

    void loadData(List<BaseData> basicDataList);

    String getAnalyticsResults();
}
