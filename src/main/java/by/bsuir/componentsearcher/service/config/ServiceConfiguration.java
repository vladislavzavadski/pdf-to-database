package by.bsuir.componentsearcher.service.config;

import by.bsuir.componentsearcher.service.Parser;
import by.bsuir.componentsearcher.service.impl.ExcelParser;
import by.bsuir.componentsearcher.service.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ulza1116 on 3/13/2017.
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public ParserFactory parserFactory(){
        return ParserFactory.getInstance();
    }

    @Bean
    public Parser excelParser(){
        return new ExcelParser();
    }

    @Bean
    public FieldWriterFactory fieldWriterFactory(){
        return FieldWriterFactory.getInstance();
    }

    @Bean
    public FieldWriter manufacturerFieldWriter(){
        return new ManufacturerFieldWriter();
    }

    @Bean
    public FieldWriter nameFieldWriter(){
        return new NameFieldWriter();
    }

    @Bean
    public FieldWriter codeFieldWriter(){
        return new CodeFieldWriter();
    }

    @Bean
    public FieldWriter priceFieldWriter(){
        return new PriceFieldWriter();
    }

    @Bean
    public WorkBookFactory workBookFactory(){
        return WorkBookFactory.getInstance();
    }
}
