package by.bsuir.componentsearcher.controller;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.ComponentService;
import by.bsuir.componentsearcher.service.exception.UnknownContentTypeException;
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
    private static final String ERROR = "error";

    @Autowired
    private ComponentService componentService;

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Component>> getByCode(@PathVariable("code") String code){
        List<Component> components = componentService.findByCode(code);
        return new ResponseEntity<>(components, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> insertFile(@Valid FieldMapping fieldMapping, @RequestParam("file") MultipartFile multipartFile, Errors errors)
            throws IOException, UnknownContentTypeException {

        if(errors.hasErrors()){
            return new ResponseEntity<>(ERROR, HttpStatus.BAD_REQUEST);
        }
        else {
            componentService.insertNewFile(fieldMapping, multipartFile);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> componentNotExist(EmptyResultDataAccessException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnknownContentTypeException.class)
    public ResponseEntity<String> unknownContentTypeException(UnknownContentTypeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
