package test.task.aliaksandar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.aliaksandar.models.document.Document;
import test.task.aliaksandar.service.AppService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAPIController {

    private final AppService appService;

    @Autowired
    public RestAPIController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Document>> getAllModels() throws Exception {
        return ResponseEntity.ok().body(appService.getList());
    }

}
