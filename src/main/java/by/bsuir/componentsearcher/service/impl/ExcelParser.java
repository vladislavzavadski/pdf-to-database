package by.bsuir.componentsearcher.service.impl;


import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulza1116 on 3/9/2017.
 */
@org.springframework.stereotype.Component
public class ExcelParser implements Parser {

    @Override
    public List<Component> parse(InputStream inputStream, RowMapper rowMapper) throws IOException {
        boolean canStartScan = false;
        List<Component> components = new ArrayList<>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);

        Sheet sheet = hssfWorkbook.getSheetAt(0);

        for (Row row : sheet) {

            if(canStartScan){
                Component component = rowMapper.rowToObject(row);
                components.add(component);
            }
            else {
                canStartScan = rowMapper.startScan(row);
            }

        }
        return components;
    }
}
