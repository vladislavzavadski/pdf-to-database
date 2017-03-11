package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.ComponentService;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    private ComponentDao componentDao;

    @Autowired
    private RowMapper rowMapper;

    @Override
    public Component findByCode(String code) {
        Component component = componentDao.findByCode(code);
        return component;
    }

    @Override
    @Transactional
    public void insertNewFile(MultipartFile multipartFile) throws IOException {
        //TODO: распарсить файл
        Parser parser = new ExcelParser();
        List<Component> components = parser.parse(multipartFile.getInputStream(), rowMapper);
        componentDao.deleteFile(multipartFile.getOriginalFilename());
        componentDao.createFile(multipartFile.getOriginalFilename());
        components.forEach(e -> e.setManufacturer("Wever&sucre"));
        componentDao.insertComponents(components, multipartFile.getOriginalFilename());

    }

    private void setManufacturerName(List<Component> components, String manufacturerName){

    }

}
