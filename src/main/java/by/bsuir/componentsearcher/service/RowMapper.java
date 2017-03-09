package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import org.apache.poi.ss.usermodel.Row;

/**
 * Created by vladislav on 09.03.17.
 */
public interface RowMapper {
    Component rowToObject(Row row);
    boolean startScan(Row row);
}
