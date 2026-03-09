package autoparts.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {
    private final String filePath;
    private static final DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    
    public FileLogger(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = "[" + timestamp + "] " + message;
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(logEntry + "\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи в лог: " + e.getMessage());
        }
        System.out.println(logEntry);
    }
}
