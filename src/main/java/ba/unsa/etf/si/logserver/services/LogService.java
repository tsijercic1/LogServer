package ba.unsa.etf.si.logserver.services;

import ba.unsa.etf.si.logserver.filters.LogFilter;
import ba.unsa.etf.si.logserver.models.Log;
import ba.unsa.etf.si.logserver.models.SimpleAction;
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

    public void saveLog(LogResponse logRequest) {
        Log log = new Log();
        log.setUsername(logRequest.getUsername());
        log.setTimestamp(logRequest.getTimestamp());
        SimpleAction action = new SimpleAction();
        action.setName(logRequest.getAction().getName());
        action.setObject(logRequest.getAction().getObject());
        action.setDescription(logRequest.getAction().getDescription());
        log.setAction(action);
        action.setLog(log);
        logRepository.save(log);
    }
}
