package ba.unsa.etf.si.logserver.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("{\"route\":\"/\"}");
    }
}
