package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ulza1116 on 3/13/2017.
 */
public final class ParserFactory {
    private static final ParserFactory parserFactory = new ParserFactory();
    private static final String CONTENT_EXCEL = "application/vnd.ms-excel";

    @Autowired
    private Parser excelParser;

    public static ParserFactory getInstance(){
        return parserFactory;
    }

    private ParserFactory(){}

    public Parser getParser(String contentType) throws UnknownContentTypeException {
        switch (contentType){
            case CONTENT_EXCEL:{
                return excelParser;
            }
            default:{
                throw new UnknownContentTypeException("Content with type " + contentType + "doesn't support");
            }
        }
    }
}
