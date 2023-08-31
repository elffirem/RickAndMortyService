package com.example.demo.controller;

import com.example.demo.model.AppProperties;
import com.example.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppPropertiesController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/getProperties")
    public AppProperties getProperties() {
        return databaseService.getProperties();
    }

    @PostMapping("/addProperties")
    public ResponseEntity<String> addProperties(@RequestBody AppProperties properties) {
        databaseService.addProperties(properties);
        return ResponseEntity.ok("Properties added successfully!");
    }
}
