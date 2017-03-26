package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.dao.MappingDao;
import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.ComponentService;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.RowMapper;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.TooMuchResultException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
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
    private ParserFactory parserFactory;

    @Override
    public Component findByCode(String code) {
        return componentDao.findByCode(code);
    }

    @Override
    public List<Component> searchComponents(Component component) throws TooMuchResultException {
        List<Component> components = componentDao.searchComponents(component);

        if(components.size()>50){
            throw new TooMuchResultException(components.size());
        }

        return components;
    }

    @Override
    @Transactional
    public void insertNewFile(MultipartFile multipartFile)
            throws IOException, UnknownContentTypeException, WriterNotFoundException, CanNotParseException {

        FieldMapping fieldMapping = mappingDao.getFileFieldMapping(multipartFile.getOriginalFilename());
        Parser parser = parserFactory.getParser(multipartFile.getContentType());

        componentDao.deleteFileComponents(multipartFile.getOriginalFilename());
        componentDao.createFile(multipartFile.getOriginalFilename());

        for (int i=0; ; i++){
            List<Component> components = parser.parse(multipartFile, fieldMapping, LIMIT, i*LIMIT);

            componentDao.insertComponents(components, multipartFile.getOriginalFilename());

            if (components.size() < LIMIT){
                return;
            }
        }

    }

}
