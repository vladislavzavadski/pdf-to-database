package by.bsuir.componentsearcher.controller;

import by.bsuir.componentsearcher.domain.Component;
import by.bsuir.componentsearcher.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by vladislav on 08.03.17.
 */
@RestController
@RequestMapping("/component")
public class FrontController {

    @Autowired
    private ComponentService componentService;

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ResponseEntity<List<Component>> getByCode(@PathVariable("code") String code){
        List<Component> components = componentService.findByCode(code);

        return new ResponseEntity<>(components, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void insertFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        componentService.insertNewFile(multipartFile);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> componentNotExist(EmptyResultDataAccessException ex){

        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
