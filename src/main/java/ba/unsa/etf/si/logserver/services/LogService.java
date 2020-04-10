package ba.unsa.etf.si.logserver.services;

import ba.unsa.etf.si.logserver.filters.LogFilter;
import ba.unsa.etf.si.logserver.repositories.LogRepository;
import ba.unsa.etf.si.logserver.responses.LogCollectionResponse;
import ba.unsa.etf.si.logserver.responses.LogResponse;
import ba.unsa.etf.si.logserver.responses.SimpleActionResponse;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public LogCollectionResponse getLogs(LogFilter logFilter) {
        return new LogCollectionResponse(
                logRepository
                        .findAllByFilter(logFilter)
                        .stream()
                        .map(
                                log ->
                                        new LogResponse(
                                                log.getUsername(),
                                                log.getTimestamp(),
                                                new SimpleActionResponse(
                                                        log.getAction().getName(),
                                                        log.getAction().getObject(),
                                                        log.getAction().getDescription()
                                                )
                                        )
                        )
                        .collect(Collectors.toList())
        );
    }
}
