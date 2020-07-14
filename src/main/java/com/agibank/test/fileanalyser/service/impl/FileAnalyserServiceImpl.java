package com.agibank.test.fileanalyser.service.impl;

import com.agibank.test.fileanalyser.model.BaseData;
import com.agibank.test.fileanalyser.processor.DataProcessor;
import com.agibank.test.fileanalyser.service.AnalyticsService;
import com.agibank.test.fileanalyser.service.FileAnalyserService;
import com.agibank.test.fileanalyser.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileAnalyserServiceImpl implements FileAnalyserService {
    private final FileService fileService;
    private final AnalyticsService analyticsService;
    private final DataProcessor dataProcessor;

    @Override
    public void run() throws IOException {
        List<BaseData> basicDataList = new ArrayList<>();
        List<File> files = fileService.readAllDatFiles();
        for (File file : files) {
            List<String> lines = fileService.readLines(file);
            for (String line : lines) {
                BaseData baseData = dataProcessor.getObject(line);
                if (Objects.nonNull(baseData)) {
                    basicDataList.add(baseData);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(basicDataList)) {
            analyticsService.loadData(basicDataList);
            fileService.saveOutPutFile(analyticsService.getAnalyticsResults());
        }
    }
}
