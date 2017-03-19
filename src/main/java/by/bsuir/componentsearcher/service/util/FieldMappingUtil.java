package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.domain.FieldMapping;

import java.util.*;

import static by.bsuir.componentsearcher.service.Parser.PLUS;
import static by.bsuir.componentsearcher.service.Parser.QUOTE;

/**
 * Created by vladislav on 19.03.17.
 */
public class FieldMappingUtil {

    public static Map<String, List<String>> getMapFieldMapping(FieldMapping fieldMapping){
        Map<String, List<String>> map = new HashMap<>();

        map.put(ColumnName.NAME, Arrays.asList(fieldMapping.getName().trim().split(PLUS)));
        map.put(ColumnName.CODE, Arrays.asList(fieldMapping.getCode().trim().split(PLUS)));

        if(!fieldMapping.getManufacturer().contains(QUOTE)) {
            map.put(ColumnName.MANUFACTURER, Arrays.asList(fieldMapping.getManufacturer().trim().split(PLUS)));
        }

        map.put(ColumnName.PRICE, Arrays.asList(fieldMapping.getPrice().trim().split(PLUS)));

        return map;
    }

    public static List<String> getFieldColumnNames(Map<String, List<String>> fieldMapping){
        List<String> result = new ArrayList<>();

        for (List<String> columns : fieldMapping.values()){
            result.addAll(columns);
        }

        return result;
    }
}
