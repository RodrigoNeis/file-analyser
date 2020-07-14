package com.agibank.test.fileanalyser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("file")
public class FileProperties {
    private String homeEnvSystemVar;

    private String inFolderPath;

    private String outFolderPath;

    private String fileEncode;

    private String outputFileName;
}
