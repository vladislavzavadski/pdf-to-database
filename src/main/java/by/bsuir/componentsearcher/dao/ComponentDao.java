package by.bsuir.componentsearcher.dao;

import by.bsuir.componentsearcher.domain.Component;

import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
public interface ComponentDao {
    Component findByCode(String code);

    List<Component> searchComponents(Component component);

    void deleteFileComponents(String fileName);

    void createFile(String fileName);

    void insertComponents(List<Component> components, String fileName);
}
