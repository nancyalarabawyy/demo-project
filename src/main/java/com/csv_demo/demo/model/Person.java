package com.csv_demo.demo.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Data
@Setter
@Getter
public class Person {
    @CsvBindByName(column = "key")
    private int key;

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "age")
    private int age;
}
