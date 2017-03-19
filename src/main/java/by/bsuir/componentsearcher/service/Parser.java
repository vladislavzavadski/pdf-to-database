package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ulza1116 on 3/9/2017.
 */
public interface Parser {
    String QUOTE = "\"";
    String EMPTY = "";
    List<Component> parse(InputStream inputStream, RowMapper<Row> rowMapper, FieldMapping fieldMapping) throws IOException, WriterNotFoundException;
}
