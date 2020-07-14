package com.agibank.test.fileanalyser;

import com.agibank.test.fileanalyser.service.FileAnalyserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;

@SpringBootApplication
@AllArgsConstructor
public class FileAnalyserApplication implements CommandLineRunner {
    private final FileSystemWatcher fileSystemWatcher;
    private final FileAnalyserService fileAnalyserService;

    public static void main(String[] args) {
        SpringApplication.run(FileAnalyserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileAnalyserService.run();
        fileSystemWatcher.start();
    }
}
