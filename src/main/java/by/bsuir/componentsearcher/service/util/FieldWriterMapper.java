package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

/**
 * Created by ulza1116 on 3/15/2017.
 */
public interface FieldWriterMapper {
    Map<Integer, FieldWriter> map(Map<String, List<String>> fieldMapping, Row row) throws WriterNotFoundException;
}
