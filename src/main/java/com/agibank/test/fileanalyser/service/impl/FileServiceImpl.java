package com.agibank.test.fileanalyser.service.impl;

import com.agibank.test.fileanalyser.config.FileProperties;
import com.agibank.test.fileanalyser.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@EnableConfigurationProperties(FileProperties.class)
@Slf4j
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileProperties fileProperties;

    @Override
    public List<File> readAllDatFiles() {
        File dir = new File(this.getFolderInPath());
        String[] extensions = new String[]{"dat"};
        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
        return files;
    }

    @Override
    public void saveOutPutFile(String outPutData) {
        File file = new File(this.getFileOutPath());
        try {
            FileUtils.writeStringToFile(file, outPutData, fileProperties.getFileEncode(), false);
            log.info("The output file = [{}] was created or updated", file.getName());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public List<String> readLines(File file) throws IOException {
        return FileUtils.readLines(file, fileProperties.getFileEncode());
    }

    public String getFolderInPath() {
        String homePath = System.getenv(fileProperties.getHomeEnvSystemVar());
        String folderInPath = homePath + fileProperties.getInFolderPath();
        log.debug("Path of in folder = [{}]", folderInPath);
        return folderInPath;
    }

    private String getFileOutPath() {
        String homePath = System.getenv(fileProperties.getHomeEnvSystemVar());
        String outFilePath = homePath + fileProperties.getOutFolderPath() + fileProperties.getOutputFileName();
        log.debug("Directory of outPutFile = [{}]", outFilePath);
        return outFilePath;
    }
}
