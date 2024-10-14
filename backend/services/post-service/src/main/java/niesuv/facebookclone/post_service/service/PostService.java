package niesuv.facebookclone.post_service.service;


import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.post_service.dto.CreatePostDto;
import niesuv.facebookclone.post_service.dto.PostReturnDTO;
import niesuv.facebookclone.post_service.entity.Post;
import niesuv.facebookclone.post_service.entity.PostImage;
import niesuv.facebookclone.post_service.exception.*;
import niesuv.facebookclone.post_service.http.UserFeignClient;
import niesuv.facebookclone.post_service.repository.PostImageRepository;
import niesuv.facebookclone.post_service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private niesuv.facebookclone.post_service.http.s3RestTemplate s3RestTemplate;

    @Autowired
    private PostImageRepository postImageRepository;


    @Autowired
    private final UserFeignClient userFeignClient;

    @Autowired
    private final AsyncS3Service asyncS3Service;

    public UUID createPost(CreatePostDto dto) {
        Post post = new Post();
        if (!userFeignClient.exists(dto.userId()))
            throw new UserNotExistException("User id does not belongs to any ID");
        Post sharePost = null;
        if (dto.shareId() != null) {
            Optional<Post> opSharePost = postRepository.findById(dto.shareId());
            if (opSharePost.isPresent())
                sharePost = opSharePost.get();
            else
                throw new PostIdNotExists("Share post ID does not exist");
        }
        post.setSharePost(sharePost);
        post.setContent(dto.content());
        post.setUserId(dto.userId());

        var id = postRepository.save(post).getId();
        if (id == null)
            throw new CreatePostException("Cannot create post due to error!");
        return id;
    }


    public UUID saveFile(MultipartFile file, UUID userId, UUID postId) {

        try {
            var type = isImageOrVideo(file);
            if (type != null) {
                Optional<Post> opPost = postRepository.findById(postId);
                if (opPost.isPresent()) {
                    UUID newImgId = UUID.randomUUID();
                    Post post = opPost.get();
                    //post/postId/imgId.png
                    String key = "post/" + post.getId().toString() + "/" + newImgId.toString() + "." + type;
                    PostImage postImage = PostImage.builder()
                            .post(post)
                            .id(newImgId)
                            .key(key)
                            .build();
                    s3RestTemplate.upload(file, key);
                    return postImageRepository.save(postImage).getId();

                } else {
                    throw new PostIdNotExists("Post ID does not belongs to any post");
                }
            } else
                throw new NotImageOrVideoException("We only accept .png, .jpg file extension");
        } catch (IOException e) {
            throw new RuntimeException("IO Exception");
        }
    }

    public String isImageOrVideo(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] headerBytes = new byte[4];
            int bytesRead = inputStream.read(headerBytes, 0, 4);  // Đọc 4 byte từ đầu file

            if (bytesRead < 4) {
                return null;  // File quá nhỏ, không hợp lệ
            }

            String fileSignature = String
                    .format("%02X %02X %02X %02X", headerBytes[0], headerBytes[1], headerBytes[2], headerBytes[3]);

            if (fileSignature.startsWith("FF D8 FF")) {
                return "jpg";  // JPEG
            } else if (fileSignature.startsWith("89 50 4E 47")) {
                return "png";  // PNG
            }
//            else if (fileSignature.startsWith("66 74 79 70")) {
//                return "mp4";
//            }
            ;  // mp4
            return null;
        }
    }

    public PostReturnDTO getPostInfo(UUID postId) {
        Optional<Post> optionalPost = postRepository.postById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            return toDTO(post);
        }
        else
            throw new PostIdNotExists("Post id does not belong to any post!");
    }


    public void clearPostByUserId(UUID userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        posts.forEach(post -> deletePost(post.getId()));
    }

    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);

        //async code
        asyncS3Service.deleteFolder("post/" + postId);
    }

    private static PostReturnDTO toDTO(Post post) {
        return PostReturnDTO.builder()
                .totalLike(post.getTotalLike())
                .shares(post.getShares())
                .createTime(post.getCreateTime())
                .images(post.getImages())
                .content(post.getContent())
                .totalComments(post.getTotalComments())
                .id(post.getId())
                .userId(post.getUserId())
                .build();
    }

}

