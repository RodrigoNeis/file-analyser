package com.agibank.test.fileanalyser.config;

import com.agibank.test.fileanalyser.listener.FileAnalyserChangeListener;
import com.agibank.test.fileanalyser.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.File;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FileWatcherProperties.class)
@Slf4j
public class FileWatcherConfig {
    private final FileAnalyserChangeListener fileAnalyserChangeListener;
    private final FileService fileService;
    private final FileWatcherProperties fileWatcherProperties;

    @Bean
    public FileSystemWatcher fileSystemWatcher() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(
                true,
                Duration.ofMillis(fileWatcherProperties.getPollInterval()),
                Duration.ofMillis(fileWatcherProperties.getQuietPeriod())
        );

        fileSystemWatcher.addSourceDirectory(new File(fileService.getFolderInPath()));
        fileSystemWatcher.addListener(fileAnalyserChangeListener);
        fileSystemWatcher.start();
        log.info("started fileSystemWatcher");
        return fileSystemWatcher;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        fileSystemWatcher().stop();
    }
}
