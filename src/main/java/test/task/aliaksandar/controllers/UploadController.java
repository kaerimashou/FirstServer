package test.task.aliaksandar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import test.task.aliaksandar.service.AppService;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private final AppService appService;

    @Autowired
    public UploadController(AppService appService) {
        this.appService = appService;
    }


    @GetMapping("/")
    public String uploadFile() {
        return "upload/uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        appService.setFile(multipartFile);
        return "redirect:/upload/";
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleStorageFileNotFound(Exception exc) {
        return ResponseEntity.notFound().build();
    }
}
