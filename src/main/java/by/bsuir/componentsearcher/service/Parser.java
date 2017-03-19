package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ulza1116 on 3/9/2017.
 */
public interface Parser {
    String PLUS = "\\+";
    String QUOTE = "\"";
    String EMPTY = "";
    List<Component> parse(MultipartFile multipartFile, FieldMapping fieldMapping, int limit, int startFrom) throws IOException, WriterNotFoundException, UnknownContentTypeException, CanNotParseException;
}
