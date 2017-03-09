package by.bsuir.componentsearcher.service;

import by.bsuir.componentsearcher.domain.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ulza1116 on 3/9/2017.
 */
public interface Parser {
    List<Component> parse(InputStream inputStream, RowMapper rowMapper) throws IOException;
}
