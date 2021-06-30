package test.task.aliaksandar.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.aliaksandar.AliaksandarApplication;
import test.task.aliaksandar.models.document.Document;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAPIController {

    private final AliaksandarApplication app=AliaksandarApplication.getInstance();

    @GetMapping("/all")
    public ResponseEntity<List<Document>> getAllModels(){
        return ResponseEntity.ok().body(app.documentList());
    }

}
