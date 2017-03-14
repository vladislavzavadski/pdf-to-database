package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
public interface ComponentService {
    List<Component> findByCode(String code);

    void insertNewFile(FieldMapping fieldMapping, MultipartFile multipartFile) throws IOException, UnknownContentTypeException;
}
