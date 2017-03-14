package by.bsuir.componentsearcher.service.impl;


import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by ulza1116 on 3/9/2017.
 */
@org.springframework.stereotype.Component
public class ExcelParser implements Parser {

    public static final String PLUS = "\\+";

    @Override
    public List<Component> parse(InputStream inputStream, RowMapper<Row> rowMapper, FieldMapping fieldMapping) throws IOException {
        boolean canStartScan = false;
        List<Component> components = new ArrayList<>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);

        Sheet sheet = hssfWorkbook.getSheetAt(0);

        for (Row row : sheet) {

            if(canStartScan){
                Component component = rowMapper.rowToObject(row, getMapFieldMapping(fieldMapping));
                components.add(component);
            }
            else {
                canStartScan = rowMapper.startScan(row, getMapFieldMapping(fieldMapping));
            }

        }
        return components;
    }

    private Map<String, List<String>> getMapFieldMapping(FieldMapping fieldMapping){
        Map<String, List<String>> map = new HashMap<>();

        map.put(ColumnName.NAME, Arrays.asList(fieldMapping.getName().trim().split(PLUS)));
        map.put(ColumnName.CODE, Arrays.asList(fieldMapping.getCode().trim().split(PLUS)));
        map.put(ColumnName.MANUFACTURER, Arrays.asList(fieldMapping.getManufacturer().trim().split(PLUS)));
        map.put(ColumnName.PRICE, Arrays.asList(fieldMapping.getPrice().trim().split(PLUS)));
        
        return map;
    }
}
