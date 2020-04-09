package ba.unsa.etf.si.logserver.responses;

import ba.unsa.etf.si.logserver.serializers.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogResponse {
    private String username;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime timestamp;

    private SimpleActionResponse action;
}
