package hu.demo.csv.service.impl;

import com.opencsv.CSVReader;
import hu.demo.csv.domain.Data;
import hu.demo.csv.dto.DataDTO;
import hu.demo.csv.repository.DataRepository;
import hu.demo.csv.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DataServiceImpl implements DataService
{
    private final DataRepository dataRepository;

    public DataServiceImpl(DataRepository dataRepository)
    {
        this.dataRepository = dataRepository;
    }


    @Override
    public void processData(String csvName)
    {
        log.trace("Process data from file {}", csvName);
        dataRepository.deleteAll();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvName)))
        {
            csvReader.readNext(); //skip header
            String[] values;
            while ((values = csvReader.readNext()) != null)
            {
                String[] csvData = values[0].split(";");
                long id = Long.parseLong(csvData[0]);
                String name = csvData[1];
                byte number = Byte.parseByte(csvData[2]);
                Data data = new Data(id, name, number);
                dataRepository.save(data);
            }

            List<Data> dataList = dataRepository.findAll();
            dataList.sort((d1, d2) -> d2.getName().compareTo(d1.getName()));
//            dataList.sort(Comparator.comparing(Data::getName).reversed());
            dataList.forEach(System.out::println);

            System.out.println("-------------------------------");

            List<Data> filteredData = dataList.stream()
                    .filter(data -> data.getNumber() >= 2 && data.getNumber() <= 6)
                    .collect(Collectors.toList());
            filteredData.forEach(System.out::println);
        }
        catch (Exception e)
        {
            log.error("Error while reading from {}", csvName);
        }
    }

    @Override
    public List<DataDTO> findAllDTO()
    {
        log.trace("Find all data DTO");
        return dataRepository.findAll().stream()
                .map(data -> {
                    DataDTO dataDTO = new DataDTO();
                    dataDTO.setName(data.getName());
                    dataDTO.setNumber(data.getNumber());
                    dataDTO.setData(data.getNumber() + ", " + data.getName());
                    return dataDTO;
                })
                .collect(Collectors.toList());
    }
}
