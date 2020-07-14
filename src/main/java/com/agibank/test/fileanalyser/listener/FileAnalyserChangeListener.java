package com.agibank.test.fileanalyser.listener;

import com.agibank.test.fileanalyser.service.FileAnalyserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileAnalyserChangeListener implements FileChangeListener {
    private final FileAnalyserService fileAnalyserService;

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        try {
            fileAnalyserService.run();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
