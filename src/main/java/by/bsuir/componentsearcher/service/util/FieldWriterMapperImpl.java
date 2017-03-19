package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ulza1116 on 3/15/2017.
 */
@Component
public class FieldWriterMapperImpl implements FieldWriterMapper {

    private FieldWriterFactory fieldWriterFactory;

    @Autowired
    public FieldWriterMapperImpl(FieldWriterFactory fieldWriterFactory){
        this.fieldWriterFactory = fieldWriterFactory;
    }

    @Override
    public Map<Integer, FieldWriter> map(Map<String, List<String>> fieldMapping, Row row) throws WriterNotFoundException {
        Map<Integer, FieldWriter> fieldWriterMap = new HashMap<>();

        for(Cell cell : row){
            String cellValue = cell.getStringCellValue().replace('\n', ' ');

            if(fieldMapping.get(ColumnName.CODE).contains(cellValue)){
                fieldWriterMap.put(cell.getColumnIndex(), fieldWriterFactory.getFieldWriter(ColumnName.CODE));
            }
            else if(fieldMapping.get(ColumnName.PRICE).contains(cellValue)){
                fieldWriterMap.put(cell.getColumnIndex(), fieldWriterFactory.getFieldWriter(ColumnName.PRICE));
            }
            else if(fieldMapping.get(ColumnName.MANUFACTURER)!=null && fieldMapping.get(ColumnName.MANUFACTURER).contains(cellValue)){
                fieldWriterMap.put(cell.getColumnIndex(), fieldWriterFactory.getFieldWriter(ColumnName.MANUFACTURER));
            }
            else if(fieldMapping.get(ColumnName.NAME).contains(cellValue)){
                fieldWriterMap.put(cell.getColumnIndex(), fieldWriterFactory.getFieldWriter(ColumnName.NAME));
            }
        }

        return fieldWriterMap;
    }

}
