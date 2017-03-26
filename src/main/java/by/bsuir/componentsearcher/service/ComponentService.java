package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.TooMuchResultException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
public interface ComponentService {
    int LIMIT = 3000;
    Component findByCode(String code);

    List<Component> searchComponents(Component component) throws TooMuchResultException;

    void insertNewFile(MultipartFile multipartFile)
            throws IOException, UnknownContentTypeException, WriterNotFoundException, CanNotParseException;
}
