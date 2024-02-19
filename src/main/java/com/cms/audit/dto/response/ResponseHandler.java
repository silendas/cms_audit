package com.cms.audit.dto.response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> responseErrorGenerator(String error, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();

            map.put("message", error);
            map.put("status", status.value());
            map.put("timestamp", new Date());

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> responseEntityGenerator(String message, HttpStatus status, Object data,
            String token) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (token == null && data == null) {
            map.put("message", message);
            map.put("status", status.value());
        } else if (token == null) {
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", data);
        } else if (data == null) {
            map.put("message", message);
            map.put("status", status.value());
            map.put("token", token);
        } else {
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", data);
            map.put("token", token);
        }

        map.put("timestamp", new Date());

        return new ResponseEntity<Object>(map, status);
    }

}
