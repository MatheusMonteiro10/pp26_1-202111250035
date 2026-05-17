package service;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private static Logger instance;

    private final List<String> logs;

    private Logger() {
        logs = new ArrayList<>();
    }

    public static Logger getInstance() {

        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void registerLog(String log) {
        logs.add(log);

        System.out.println("[LOG] " + log);
    }

    public String getAllLogs() {

        StringBuilder builder = new StringBuilder();

        for (String log : logs) {
            builder.append(log).append("\n");
        }

        return builder.toString();
    }
}