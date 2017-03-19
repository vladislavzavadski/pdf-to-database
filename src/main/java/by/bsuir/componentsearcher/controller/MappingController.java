package by.bsuir.componentsearcher.controller;

import by.bsuir.componentsearcher.dao.ComponentDao;
import by.bsuir.componentsearcher.domain.FieldMapping;
import by.bsuir.componentsearcher.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by vladislav on 19.03.17.
 */
@RestController
public class MappingController {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private ComponentDao componentDao;

    @RequestMapping(value = "/mapping", method = RequestMethod.POST)
    public ResponseEntity<String> addColumnMapping(@RequestBody @Valid FieldMapping fieldMapping, Errors errors){

        if(errors.hasErrors()){
            return new ResponseEntity<>("Invalid field mapping", HttpStatus.BAD_REQUEST);
        }
        else {
            componentDao.createFile(fieldMapping.getFileName());
            mappingService.addFieldMapping(fieldMapping);

            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
}
