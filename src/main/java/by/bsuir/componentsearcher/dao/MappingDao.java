package by.bsuir.componentsearcher.dao;

import by.bsuir.componentsearcher.domain.FieldMapping;

/**
 * Created by vladislav on 12.03.17.
 */
public interface MappingDao {
    void addMapping(FieldMapping fieldMapping);
    FieldMapping getFileFieldMapping(String fileName);
}
