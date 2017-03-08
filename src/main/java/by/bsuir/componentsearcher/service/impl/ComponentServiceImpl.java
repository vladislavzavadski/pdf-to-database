package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

/**
 * Created by vladislav on 08.03.17.
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentDao componentDao;

    @Override
    public Component findByCode(String code) {
        return componentDao.findByCode(code);
    }

    @Override
    @Transactional
    public void insertNewFile(MultipartFile multipartFile){
        //TODO: распарсить файл
        componentDao.deleteFile(multipartFile.getOriginalFilename());
        componentDao.createFile(multipartFile.getOriginalFilename());
        componentDao.insertComponents(Collections.emptyList(), multipartFile.getOriginalFilename());

    }

}
