package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.ResponseComponent;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.InvalidParameterException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by vladislav on 08.03.17.
 */
public interface ComponentService {
    int LIMIT = 3000;
    Component findByCode(String code);

    ResponseComponent searchComponents(Component component, int limit, int startFrom) throws InvalidParameterException;

    void insertNewFile(MultipartFile multipartFile)
            throws IOException, UnknownContentTypeException, WriterNotFoundException, CanNotParseException;
}
