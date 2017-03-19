package by.bsuir.componentsearcher.controller;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.ComponentService;
import by.bsuir.componentsearcher.service.exception.CanNotParseException;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
import by.bsuir.componentsearcher.service.exception.WriterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
@RestController
@RequestMapping("/component")
public class FrontController {

    private static final String SUCCESS = "success";

    @Autowired
    private ComponentService componentService;

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseEntity<Component> getByCode(@PathVariable("code") String code){
        Component component = componentService.findByCode(code);
        return new ResponseEntity<>(component, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> insertFile(@RequestParam("file") MultipartFile multipartFile)
            throws IOException, UnknownContentTypeException, WriterNotFoundException, CanNotParseException {

        componentService.insertNewFile(multipartFile);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> componentNotExist(EmptyResultDataAccessException ex){
        return new ResponseEntity<>("Data not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnknownContentTypeException.class)
    public ResponseEntity<String> unknownContentTypeException(UnknownContentTypeException ex){
        return new ResponseEntity<>("Invalid content type", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CanNotParseException.class)
    public ResponseEntity<String> canNotParseException(CanNotParseException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
