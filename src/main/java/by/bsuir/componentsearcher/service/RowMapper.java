package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.ApplicationContext;

/**
 * Created by vladislav on 09.03.17.
 */
public interface RowMapper<T> {
    Component rowToObject(T t);
    boolean startScan(T t);
}
