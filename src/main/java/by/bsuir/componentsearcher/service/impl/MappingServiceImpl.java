package by.bsuir.componentsearcher.service.impl;

import by.bsuir.componentsearcher.dao.MappingDao;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vladislav on 19.03.17.
 */
@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    private MappingDao mappingDao;

    @Override
    public void addFieldMapping(FieldMapping fieldMapping) {
        mappingDao.addMapping(fieldMapping);
    }

}
