package hu.demo.csv.service;

import hu.demo.csv.dto.DataDTO;

import java.util.List;

public interface DataService
{
    void processData(String csvName);

    List<DataDTO> findAllDTO();
}
