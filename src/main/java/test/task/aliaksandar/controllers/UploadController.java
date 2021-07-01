package test.task.aliaksandar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.service.implementation.AppServiceImpl;

@Controller
@RequestMapping("/test")
public class UploadController {

    private final AppServiceImpl appServiceImpl;

    @Autowired
    public UploadController(AppServiceImpl appServiceImpl) {
        this.appServiceImpl = appServiceImpl;
    }


    @GetMapping("/upload")
    public String uploadFile() {
        return "upload/uploadForm";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        appServiceImpl.post(multipartFile);
        return "redirect:/test/upload/";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleStorageFileNotFound(Exception exc) {
        return ResponseEntity.notFound().build();
    }
}
