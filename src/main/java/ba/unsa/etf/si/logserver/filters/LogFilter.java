package ba.unsa.etf.si.logserver.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogFilter {
    private String username;
    private LocalDateTime from;
    private LocalDateTime to;
    private String action;
    private String object;
}
