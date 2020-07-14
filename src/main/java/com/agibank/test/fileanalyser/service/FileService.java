package com.agibank.test.fileanalyser.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    List<File> readAllDatFiles();

    void saveOutPutFile(String outPutData);

    String getFolderInPath();

    List<String> readLines(File file) throws IOException;
}
