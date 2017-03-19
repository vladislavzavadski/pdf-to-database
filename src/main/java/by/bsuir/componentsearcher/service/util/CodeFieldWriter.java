package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.domain.Component;

/**
 * Created by vladislav on 15.03.17.
 */
public class CodeFieldWriter implements FieldWriter {

    @Override
    public void setField(Component component, String fieldValue) {

        if(component.getCode() == null) {
            component.setCode(fieldValue);
        }
        else {
            component.setCode(component.getCode() + EMPTY_STRING + fieldValue);
        }
    }
}
