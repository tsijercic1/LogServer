package ba.unsa.etf.si.logserver.controllers;

import ba.unsa.etf.si.logserver.responses.NotificationResponse;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final SimpMessageSendingOperations notificationBroadcaster;

    public NotificationController(SimpMessageSendingOperations notificationBroadcaster) {
        this.notificationBroadcaster = notificationBroadcaster;
    }

    @PostMapping("/notify/{app}")
    public NotificationResponse notifyListeners(@PathVariable String app, @RequestBody NotificationResponse response) {
        notificationBroadcaster.convertAndSend("/topic/" + app, response);
        return response;
    }
}
