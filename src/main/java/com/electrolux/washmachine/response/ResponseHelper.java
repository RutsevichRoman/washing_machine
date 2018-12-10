package com.electrolux.washmachine.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper {

    @Autowired
    public ResponseHelper() {

    }

    public ResponseEntity notFound(String key) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(key);
    }

    public <Body> ResponseEntity<Body> ok(Body body) {
        return ResponseEntity.ok().body(body);
    }

    public ResponseEntity okStatus(String key) {
        return ResponseEntity.status(HttpStatus.OK).body(key);
    }

    public ResponseEntity badStatus(String key) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(key);
    }
}
