package com.agibank.test.fileanalyser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("file-watcher")
public class FileWatcherProperties {
    private long pollInterval;
    private long quietPeriod;
}
