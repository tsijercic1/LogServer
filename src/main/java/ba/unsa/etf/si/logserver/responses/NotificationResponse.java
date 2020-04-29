package ba.unsa.etf.si.logserver.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    /**
     * private String type; // success, info, warning, error
     * private Payload payload;
     * -----
     *      private String action; // login
     *      private String subject; // somebody
     *      private String description; // somebody has just logged into the merchant web application
     *
     * {
     *     "type": "info",
     *     "payload": {
     *         "action": "login",
     *         "subject": "Savudin Šimširpašić",
     *         "description": "Savudin Šimširpašić has just logged into the Merchant Web app"
     *     }
     * }
     */
    private String type;
    private NotificationPayload payload;
}
