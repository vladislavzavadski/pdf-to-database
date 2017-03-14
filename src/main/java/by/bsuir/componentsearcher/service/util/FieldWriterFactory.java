package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.ColumnName;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vladislav on 15.03.17.
 */
public final class FieldWriterFactory {

    @Autowired
    private FieldWriter manufacturerFieldWriter;

    @Autowired
    private FieldWriter priceFieldWriter;

    @Autowired
    private FieldWriter nameFieldWriter;

    @Autowired
    private FieldWriter codeFieldWriter;

    private static final FieldWriterFactory ourInstance = new FieldWriterFactory();

    public static FieldWriterFactory getInstance() {
        return ourInstance;
    }

    private FieldWriterFactory() {
    }

    public FieldWriter getFieldWriter(String fieldName) throws WriterNotFoundException {

        switch (fieldName){
            case ColumnName.CODE:{
                return codeFieldWriter;
            }

            case ColumnName.NAME:{
                return nameFieldWriter;
            }

            case ColumnName.PRICE:{
                return priceFieldWriter;
            }

            case ColumnName.MANUFACTURER:{
                return manufacturerFieldWriter;
            }

            default:{
                throw new WriterNotFoundException("Can not find writer with name "+fieldName);
            }

        }
    }
}
