package ba.unsa.etf.si.logserver.controllers;

import ba.unsa.etf.si.logserver.filters.LogFilter;
import ba.unsa.etf.si.logserver.responses.LogCollectionResponse;
import ba.unsa.etf.si.logserver.responses.LogResponse;
import ba.unsa.etf.si.logserver.services.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RestController
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("{\"message\":\"Hiii!\"}");
    }

    @GetMapping("/logs")
    public ResponseEntity<LogCollectionResponse> getLogs(
            @RequestParam(name = "username",required = false) String username,
            @RequestParam(name = "from",required = false) Long from,
            @RequestParam(name = "to",required = false) Long to,
            @RequestParam(name = "action",required = false) String action,
            @RequestParam(name = "object",required = false) String object
            ) {
        LogFilter logFilter = new LogFilter(
                username,
                from!=null?LocalDateTime.ofEpochSecond(from, 0, OffsetDateTime.now().getOffset()):null,
                to!=null?LocalDateTime.ofEpochSecond(to, 0, OffsetDateTime.now().getOffset()):null,
                action,
                object
        );
        return ResponseEntity
                .ok(
                        logService.getLogs(logFilter)
                );
    }

    @PostMapping(value = "/logs", produces = "application/json")
    public ResponseEntity<String> postLog(@RequestBody LogResponse logRequest) {
        logService.saveLog(logRequest);
        return ResponseEntity.ok("{\"message\":\"Successfully added log!\"}");
    }


}
