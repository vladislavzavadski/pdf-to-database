package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.dao.MappingDao;
import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.domain.ResponseComponent;
import by.bsuir.componentsearcher.service.ComponentService;
import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.InvalidParameterException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import by.bsuir.componentsearcher.service.util.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
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
    public ResponseComponent searchComponents(Component component, int limit, int startFrom) throws InvalidParameterException {

        if(limit <= 0){
            throw new InvalidParameterException("Parameter limit must be greater than 0");
        }
        if(startFrom < 0){
            throw new InvalidParameterException("Parameter start from must be greater or equals to 0");
        }

        int componentNumber = componentDao.getSearchedComponentsNumber(component);

        List<Component> components = Collections.emptyList();

        if(componentNumber != 0){
            components = componentDao.searchComponents(component, limit, startFrom);
        }

        return new ResponseComponent(componentNumber, components);
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
