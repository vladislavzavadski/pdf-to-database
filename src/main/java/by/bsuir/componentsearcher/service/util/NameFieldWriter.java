package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.domain.Component;

/**
 * Created by vladislav on 15.03.17.
 */
public class NameFieldWriter implements FieldWriter {

    @Override
    public void setField(Component component, String fieldValue) {
        component.setName(fieldValue);
    }
}
