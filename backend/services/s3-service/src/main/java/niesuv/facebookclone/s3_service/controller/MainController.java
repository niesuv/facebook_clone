package niesuv.facebookclone.s3_service.controller;


import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.s3_service.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private S3Service s3Service;

    @GetMapping("")
    public Object listBucket() {
        return s3Client.listBuckets().buckets().stream().map(s -> s.name()).toList();
    }

    @PutMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file) {
        if (s3Service.uploadFile(file))
            return ResponseEntity.ok("Upload file Successfully");
        return ResponseEntity.badRequest().body("Error");
    }

    @DeleteMapping("/deletefolder")
    public ResponseEntity<Object> deleteFolder(@RequestParam("folder") String folder) {
        if (s3Service.deleteFolder(folder))
            return ResponseEntity.ok("Delete folder Successfully");
        return ResponseEntity.badRequest().body("Error");
    }

}