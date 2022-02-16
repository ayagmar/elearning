package com.emsi.pfa.elearning.web;

import com.emsi.pfa.elearning.model.Util.FormHelperClass;
import com.emsi.pfa.elearning.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/save")
    public String saveDossier(@RequestPart FormHelperClass.postForm form, @RequestPart("document") List<MultipartFile> multipartFile) throws IOException {
        return postService.CreatePost(form,multipartFile);
    }
}
