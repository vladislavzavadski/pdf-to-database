package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.domain.Component;

/**
 * Created by vladislav on 15.03.17.
 */
public interface FieldWriter {
    String EMPTY_STRING = " ";
    void setField(Component component, String fieldValue);
}
