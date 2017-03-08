package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vladislav on 08.03.17.
 */
public interface ComponentService {
    Component findByCode(String code);

    void insertNewFile(MultipartFile multipartFile);
}
