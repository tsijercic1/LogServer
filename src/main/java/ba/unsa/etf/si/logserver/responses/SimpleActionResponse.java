package ba.unsa.etf.si.logserver.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleActionResponse {
    private String name;
    private String object;
    private String description;
}
