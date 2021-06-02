package de.l0x.homepage.web.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class FileBufferedRequestLogger implements RequestLogger {

    private final String path;
    private BufferedWriter writer;
    private final LogDataExtractor extractor;

    public FileBufferedRequestLogger(@Value("${log.file}") String path, LogDataExtractor extractor)
    {
        this.path = path;
        this.extractor = extractor;
    }

    @PostConstruct
    private void initialize() {
        try {
            if (writer != null) {
                writer.close();
            }
            writer = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            log.error("Couldn't initialize log writer.", e);
        }
    }

    @Override
    public void log(HttpServletRequest request) {
        List<String> data = extractor.extractFrom(request);
        String dataStr = String.join("\t", data);

        try {
            writer.write(dataStr);
            writer.write('\n');
        } catch (IOException e) {
            log.warn("Failed to write to file. Trying to re-initialize...", e);
            initialize();
        }
    }

    @Scheduled(fixedDelayString = "${log.flush.cycle}")
    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            log.warn("Failed to flush writer. Trying to re-initialize...", e);
            initialize();
        }
    }

}
