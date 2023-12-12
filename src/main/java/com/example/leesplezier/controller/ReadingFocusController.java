package com.example.leesplezier.controller;

import com.example.leesplezier.dto.ReadingFocusDto;
import com.example.leesplezier.service.ReadingFocusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/reading_focus")
public class ReadingFocusController {
private final ReadingFocusService rfService;
public ReadingFocusController(ReadingFocusService rfService){
    this.rfService = rfService;
}

@GetMapping
    public ResponseEntity<List<ReadingFocusDto>> getAllRF(){
    List<ReadingFocusDto> rfDtos = rfService.getAllReadingFoci();
    return ResponseEntity.ok(rfDtos);
}


}
