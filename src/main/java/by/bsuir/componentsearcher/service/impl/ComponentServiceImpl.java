package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.dao.MappingDao;
import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.ComponentService;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.util.ParserFactory;
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
    private MappingDao mappingDao;

    @Autowired
    private RowMapper rowMapper;

    @Autowired
    private ParserFactory parserFactory;

    @Override
    public List<Component> findByCode(String code) {
        return componentDao.findByCode(code);
    }

    @Override
    @Transactional
    public void insertNewFile(FieldMapping fieldMapping, MultipartFile multipartFile) throws IOException, UnknownContentTypeException {
        //TODO: распарсить файл
        if(fieldMapping != null){
            componentDao.createFile(multipartFile.getOriginalFilename());
            mappingDao.addMapping(fieldMapping, multipartFile.getOriginalFilename());
        }
        else {
            fieldMapping = mappingDao.getFileFieldMapping(multipartFile.getOriginalFilename());
        }

        Parser parser = parserFactory.getParser(multipartFile.getContentType());
        List<Component> components = parser.parse(multipartFile.getInputStream(), rowMapper);

        componentDao.deleteFileComponents(multipartFile.getOriginalFilename());
        componentDao.createFile(multipartFile.getOriginalFilename());

        componentDao.insertComponents(components, multipartFile.getOriginalFilename());

    }

}
