package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.RowMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

/**
 * Created by vladislav on 09.03.17.
 */
/*
* Подумать по поводу того, где что лежит.
* Значение из колонки, куда его писать (description, name, code, manufacturer)
* Можно создать Map, где ключом будет номер столбца, а значением - интерфейс FieldWriter
* В нем можно объявить 1 метод writeField, оторый будет писать уже в конкретное поле.*/
@org.springframework.stereotype.Component
public class ExcelRowMapper implements RowMapper<Row> {

    private static final String ARTICLE_NUMBER = "ARTICLE NUMBER";
    private static final String EMPTY_STRING = " ";

    @Override
    public boolean startScan(Row row, Map<String, List<String>> fieldMapping) {

        nextCell:
        for(Cell cell : row){

            if(cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING){
                return false;
            }

            for(List<String> columnNames : fieldMapping.values()){

                String cellValue = cell.getStringCellValue();

                if(columnNames.contains(cellValue)){
                    continue nextCell;
                }
            }

            return false;

        }
        //TODO : Test this method
        return true;
    }

    @Override
    public Component rowToObject(Row row, Map<String, List<String>> fieldMapping) {
        Component component = new Component();

        Cell cell = row.getCell(0);

        cell.setCellType(Cell.CELL_TYPE_STRING);
        component.setCode(cell.getStringCellValue());
        
        cell = row.getCell(1);
        component.setName(cell.getStringCellValue());

        cell = row.getCell(2);
        component.setName(component.getName() + EMPTY_STRING + cell.getStringCellValue());

        cell = row.getCell(3);
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            component.setPrice(cell.getNumericCellValue());
        }

        return component;
    }
}
