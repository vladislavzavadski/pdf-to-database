package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by vladislav on 08.03.17.
 */
public interface ComponentService {
    Component findByCode(String code);

    void insertNewFile(MultipartFile multipartFile) throws IOException;
}
