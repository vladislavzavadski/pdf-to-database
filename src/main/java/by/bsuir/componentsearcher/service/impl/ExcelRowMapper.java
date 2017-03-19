package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.RowMapper;
import by.bsuir.componentsearcher.service.util.FieldWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vladislav on 09.03.17.
 */

@org.springframework.stereotype.Component
public class ExcelRowMapper implements RowMapper<Row> {

    @Override
    public boolean startScan(Row row, Map<String, List<String>> fieldMapping) {
        List<String> columns = new ArrayList<>();

        for (List<String> strings: fieldMapping.values()) {
            columns.addAll(strings);
        }

        for (Cell cell : row){
            if(cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING){
                columns.remove(cell.getStringCellValue().replace('\n', ' '));
            }
        }

        return columns.isEmpty();
    }

    @Override
    public Component rowToObject(Row row, Map<Integer, FieldWriter> fieldWriterMap) {
        Component component = new Component();

        for (Cell cell : row){
            FieldWriter fieldWriter = fieldWriterMap.get(cell.getColumnIndex());

            if (fieldWriter != null){
                cell.setCellType(Cell.CELL_TYPE_STRING);
                fieldWriter.setField(component, cell.getStringCellValue());
            }
        }

        return component;
    }
}
