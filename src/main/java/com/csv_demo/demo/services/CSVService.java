package com.csv_demo.demo.services;

import com.csv_demo.demo.model.Person;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CSVService {
    public List<Person> readCSV() {
        try {
            ClassPathResource src = new ClassPathResource("persons.csv");
            Reader reader = new InputStreamReader(src.getInputStream());
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return  csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: ", e);
        }
    }

    public void writeToCSV(Person person) {
        try {
            File file = new ClassPathResource("persons.csv").getFile();
            FileWriter fw = new FileWriter(file, true);

            fw.append(
                    person.getId() + "," +
                            person.getName() + "," +
                            person.getAge() + "\n"
            );
            fw.flush();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException("Error writing to CSV file: ", e);
        }
    }
    public void deleteCSV(int id) {
        try {
            ClassPathResource src = new ClassPathResource("persons.csv");
            Reader reader = new InputStreamReader(src.getInputStream());
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(0)
                    .build();

            List<Person> persons = csvToBean.parse();

            persons.removeIf(person -> person.getId() == id);

            File file = new ClassPathResource("persons.csv").getFile();
            FileWriter fw = new FileWriter(file);
            fw.append("id,name,age\n");
            for (Person person : persons) {
                fw.append(String.valueOf(person.getId())).append(",").append(person.getName()).append(",").append(String.valueOf(person.getAge())).append("\n");
            }

            fw.flush();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting row from CSV file: ", e);
        }
    }

}
