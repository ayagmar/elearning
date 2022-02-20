package com.emsi.pfa.elearning.service;

import com.emsi.pfa.elearning.dao.DocumentRepository;
import com.emsi.pfa.elearning.dao.PostRepository;
import com.emsi.pfa.elearning.model.Document;
import com.emsi.pfa.elearning.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


@Service
@Transactional
public class DocumentService {
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads";
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PostRepository postRepository;

    public ResponseEntity<String> uploadFilesToPost(Long id, List<MultipartFile> multipartFile) throws IOException {
        Post post = postRepository.getById(id);
        for (MultipartFile file : multipartFile) {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path path = get(DIRECTORY, filename).toAbsolutePath().normalize();
            Document document = new Document();
            document.setPost(post);
            document.setTypeDocument(file.getContentType());
            document.setName(filename);
            document.setContent(file.getBytes());
            document.getPost().setNb_documents(document.getPost().getNb_documents() + 1);
            copy(file.getInputStream(), path, REPLACE_EXISTING);
            documentRepository.save(document);

        }
        return ResponseEntity.ok().body("Uploaded " + multipartFile.size() + " files successfully.");
    }

    public ResponseEntity<String> downloadFileFromDB(Long id, HttpServletResponse response) throws Exception {
        Optional<Document> result = documentRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Could not find Document with id" + id);
        }
        Document document = result.get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getName();
        response.setHeader(headerKey, headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(document.getContent());
        outputStream.close();
        return ResponseEntity.ok().body("testhh");
    }

    public ResponseEntity<Resource> downloadFileFromServer(Long id) throws IOException {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            Path filepath = get(DIRECTORY).toAbsolutePath().normalize().resolve(document.get().getName());

            if (!Files.exists(filepath)) {
                return ResponseEntity.badRequest().build();
            }

            Resource resource = new UrlResource(filepath.toUri());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("File-name", document.get().getName());
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());


            return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filepath)))
                    .headers(httpHeaders).body(resource);
        }
        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty()) {
            return ResponseEntity.badRequest().body("Dossier not found");
        }
        documentRepository.delete(document.get());
        return ResponseEntity.ok().body("Dossier deleted succesfully");
    }

}