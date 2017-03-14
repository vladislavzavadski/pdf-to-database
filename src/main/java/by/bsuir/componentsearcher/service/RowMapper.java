package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by vladislav on 09.03.17.
 */
public interface RowMapper<T> {
    Component rowToObject(T t, Map<String, List<String>> fieldMapping);
    boolean startScan(T t, Map<String, List<String>> fieldMapping);
}
