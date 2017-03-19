package by.bsuir.componentsearcher.service.impl;


import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import by.bsuir.componentsearcher.service.util.FieldMappingUtil;
import by.bsuir.componentsearcher.service.util.FieldWriter;
import by.bsuir.componentsearcher.service.util.FieldWriterMapper;
import by.bsuir.componentsearcher.service.util.WorkBookFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

    @Autowired
    private RowMapper<Row> rowMapper;

    @Override
    public List<Component> parse(MultipartFile multipartFile, FieldMapping fieldMapping,
                                 int limit, int startFrom)
            throws IOException, WriterNotFoundException, UnknownContentTypeException, CanNotParseException {

        Workbook workBook = workBookFactory.getWorkBook(multipartFile);

        Sheet sheet = workBook.getSheetAt(0);

        Map<String, List<String>> mapFieldMapping = FieldMappingUtil.getMapFieldMapping(fieldMapping);

        List<String> columnNames = FieldMappingUtil.getFieldColumnNames(mapFieldMapping);

        int startScanRow = getStartScanRowNumber(sheet, columnNames);

        Map<Integer, FieldWriter> fieldWriterMap = fieldWriterMapper.map(mapFieldMapping, sheet.getRow(startScanRow));

        List<Component> result = scanComponents(sheet, fieldWriterMap, limit, startScanRow+1+startFrom);

        if(fieldMapping.getManufacturer().contains(QUOTE)){
            result.forEach(e -> e.setManufacturer(fieldMapping.getManufacturer().replaceAll(QUOTE, EMPTY)));
        }

        return result;
    }

    private int getStartScanRowNumber(Sheet sheet, List<String> columnNames) throws CanNotParseException {

        for(int i=0; i<sheet.getPhysicalNumberOfRows(); i++){

            if(rowMapper.startScan(sheet.getRow(i), columnNames)){
                return i;
            }

        }

        throw new CanNotParseException("Current file is empty or invalid field mapping");
    }

    private List<Component> scanComponents(Sheet sheet, Map<Integer, FieldWriter> fieldWriterMap, int limit, int startFrom){

        List<Component> result = new ArrayList<>();

        for (int i=startFrom; i<sheet.getPhysicalNumberOfRows() && (i-startFrom < limit); i++){
            Component component = rowMapper.rowToObject(sheet.getRow(i), fieldWriterMap);
            result.add(component);
        }

        return result;

    }

}
