package com.electrolux.washmachine.response;

import com.electrolux.washmachine.localization.Localization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper {

    private final Localization localization;

    @Autowired
    public ResponseHelper(Localization localization) {
        this.localization = localization;
    }

    public ResponseEntity badRequest(String key) {
        return ResponseEntity.badRequest().body(localization.getString(key));
    }

    public <Body> ResponseEntity<Body> badRequestWithBody(Body body) {
        return ResponseEntity.badRequest().body(body);
    }

    public <Body> ResponseEntity<Body> okWithBody(Body body) {
        return ResponseEntity.ok(body);
    }

    public ResponseEntity ok(String key) {
        return ResponseEntity.ok(localization.getString(key));
    }
}
