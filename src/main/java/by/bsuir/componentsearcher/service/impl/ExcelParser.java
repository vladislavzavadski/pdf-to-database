package by.bsuir.componentsearcher.service.impl;


import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import by.bsuir.componentsearcher.service.util.FieldMappingUtil;
import by.bsuir.componentsearcher.service.util.FieldWriter;
import by.bsuir.componentsearcher.service.util.FieldWriterMapper;
import by.bsuir.componentsearcher.service.util.WorkBookFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Created by ulza1116 on 3/9/2017.
 */
@org.springframework.stereotype.Component
public class ExcelParser implements Parser {

    @Autowired
    private FieldWriterMapper fieldWriterMapper;

    @Autowired
    private WorkBookFactory workBookFactory;

    @Override
    public List<Component> parse(MultipartFile multipartFile, RowMapper<Row> rowMapper, FieldMapping fieldMapping) throws IOException, WriterNotFoundException, UnknownContentTypeException {

        boolean canStartScan = false;
        List<Component> components = new ArrayList<>();
        Workbook hssfWorkbook = workBookFactory.getWorkBook(multipartFile);

        Sheet sheet = hssfWorkbook.getSheetAt(0);
        Map<String, List<String>> mapFieldMapping = FieldMappingUtil.getMapFieldMapping(fieldMapping);

        Map<Integer, FieldWriter> fieldWriterMap = null;

        for (Row row : sheet) {

            if(canStartScan){
                Component component = rowMapper.rowToObject(row, fieldWriterMap);
                components.add(component);
            }
            else {
                canStartScan = rowMapper.startScan(row, mapFieldMapping);

                if(canStartScan){
                    fieldWriterMap = fieldWriterMapper.map(mapFieldMapping, row);
                }
            }

        }

        if(fieldMapping.getManufacturer().contains(QUOTE)){
            components.forEach(e -> e.setManufacturer(fieldMapping.getManufacturer().replaceAll(QUOTE, EMPTY)));
        }

        return components;
    }

}
