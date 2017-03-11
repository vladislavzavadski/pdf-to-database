package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.RowMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by vladislav on 09.03.17.
 */
@org.springframework.stereotype.Component
public class ExcelRowMapper implements RowMapper<Row> {

    private static final String ARTICLE_NUMBER = "ARTICLE NUMBER";
    private static final String EMPTY_STRING = " ";

    @Override
    public boolean startScan(Row row) {
        Cell cell = row.getCell(0);

        return cell!= null && cell.getStringCellValue().equalsIgnoreCase(ARTICLE_NUMBER);
    }

    @Override
    public Component rowToObject(Row row) {
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
