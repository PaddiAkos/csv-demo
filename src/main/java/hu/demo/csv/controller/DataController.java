package hu.demo.csv.controller;

import hu.demo.csv.dto.DataDTO;
import hu.demo.csv.service.DataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class DataController
{
    private final DataService dataService;

    @GetMapping
    public ResponseEntity<List<DataDTO>> getData() {
        return ResponseEntity.ok(dataService.findAllDTO());
    }
}
