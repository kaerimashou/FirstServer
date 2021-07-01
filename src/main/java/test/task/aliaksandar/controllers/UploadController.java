package test.task.aliaksandar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.models.document.Document;
import test.task.aliaksandar.service.AppService;

import java.util.List;

@Controller
@RequestMapping("/test")
public class UploadController {

    private final AppService appService;

    @Autowired
    public UploadController(AppService appService) {
        this.appService = appService;
    }


    @GetMapping("/upload")
    public String uploadFile() {
        return "upload/uploadForm";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        appService.setList(multipartFile);
        appService.post();
        return "redirect:/test/upload/";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleStorageFileNotFound(Exception exc) {
        return ResponseEntity.notFound().build();
    }
}
