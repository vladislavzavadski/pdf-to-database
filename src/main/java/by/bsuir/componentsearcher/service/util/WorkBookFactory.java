package by.bsuir.componentsearcher.service.util;

import by.bsuir.componentsearcher.ContentType;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by vladislav on 19.03.17.
 */
public class WorkBookFactory {

    private static final WorkBookFactory ourInstance = new WorkBookFactory();

    public static WorkBookFactory getInstance() {
        return ourInstance;
    }

    private WorkBookFactory() {
    }

    public Workbook getWorkBook(MultipartFile multipartFile) throws IOException, UnknownContentTypeException {
        switch (multipartFile.getContentType()){
            case ContentType.CONTENT_EXCEL : {
                return new HSSFWorkbook(multipartFile.getInputStream());
            }
            case ContentType.SPRED_SHEET : {
                return new XSSFWorkbook(multipartFile.getInputStream());
            }

            default:{
                throw new UnknownContentTypeException("Content type " + multipartFile.getContentType()+" is unknown");
            }
        }
    }
}
