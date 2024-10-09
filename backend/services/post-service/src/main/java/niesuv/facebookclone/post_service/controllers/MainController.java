package niesuv.facebookclone.post_service.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.post_service.dto.CreateCommentDTO;
import niesuv.facebookclone.post_service.dto.CreateLikeDTO;
import niesuv.facebookclone.post_service.dto.CreatePostDto;
import niesuv.facebookclone.post_service.dto.PostReturnDTO;
import niesuv.facebookclone.post_service.service.CommentService;
import niesuv.facebookclone.post_service.service.LikeService;
import niesuv.facebookclone.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createPost(@RequestBody CreatePostDto dto) {
        return postService.createPost(dto);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam("file") MultipartFile file
            , @RequestParam("userId") UUID userId, @RequestParam("postId") UUID postId) {
        postService.saveFile(file, userId, postId);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostReturnDTO getPost(@PathVariable("postId") UUID postId) {
        return postService.getPostInfo(postId);
    }

    @PutMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addComment(@Valid @RequestBody CreateCommentDTO dto) {
        return commentService.addComment(dto);
    }

    @PutMapping("/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addLike(@Valid @RequestBody CreateLikeDTO dto) {
        return likeService.addLike(dto);
    }


}
