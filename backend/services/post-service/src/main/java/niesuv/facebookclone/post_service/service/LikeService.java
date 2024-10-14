package niesuv.facebookclone.post_service.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.post_service.dto.CreateLikeDTO;
import niesuv.facebookclone.post_service.entity.Comment;
import niesuv.facebookclone.post_service.entity.Like;
import niesuv.facebookclone.post_service.entity.Post;
import niesuv.facebookclone.post_service.exception.CreatePostException;
import niesuv.facebookclone.post_service.exception.PostIdNotExists;
import niesuv.facebookclone.post_service.exception.UnValidInput;
import niesuv.facebookclone.post_service.exception.UserNotExistException;
import niesuv.facebookclone.post_service.http.UserFeignClient;
import niesuv.facebookclone.post_service.repository.CommentRepository;
import niesuv.facebookclone.post_service.repository.LikeRepository;
import niesuv.facebookclone.post_service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CommentRepository commentRepository;

    public UUID addLike(@Valid CreateLikeDTO dto) {
        if (!userFeignClient.exists(dto.userId())) {
            throw new UserNotExistException("User does not exist");
        }
        if (dto.commentId() == null && dto.postId() == null)
            throw new UnValidInput("You cannot pass both postId and commentId to null");

        if (dto.commentId() != null && dto.postId() != null)
            throw new UnValidInput("You cannot pass both postId and commentId");

        boolean isPost = dto.postId() != null;

        if (isPost) {
            Optional<Post> optionalPost = postRepository.findById(dto.postId());
            if (optionalPost.isEmpty())
                throw new PostIdNotExists("Post does not exist");
            Post post = optionalPost.get();
            var id = likeRepository.save(Like.builder()
                    .post(post)
                    .userId(dto.userId())
                    .build()
            ).getId();
            if (id == null)
                throw new CreatePostException("Cannot create like");

            post.setTotalLike(post.getTotalLike() + 1);
            postRepository.save(post);
            return id;
        }

        Optional<Comment> optionalComment = commentRepository.findById(dto.commentId());
        if (optionalComment.isEmpty())
            throw new PostIdNotExists("commentId does not exist");
        Comment comment = optionalComment.get();
        var id = likeRepository.save(Like.builder()
                .comment(comment)
                .userId(dto.userId())
                .build()
        ).getId();
        if (id == null)
            throw new CreatePostException("Cannot create like");

        comment.setTotalLikes(comment.getTotalLikes() + 1);
        commentRepository.save(comment);
        return id;


    }


    public void deleteLike(UUID likeId) {
    }

    public void deleteLike(Like like) {
        Post post = like.getPost();
        if (post != null) {
            post.setTotalLike(post.getTotalLike() - 1);
            postRepository.save(post);
        }
        Comment comment = like.getComment();
        if (comment != null) {
            comment.setTotalLikes(comment.getTotalLikes() - 1);
            commentRepository.save(comment);
        }
        likeRepository.delete(like);
    }



    public void clearAllLikeByUserId(UUID userId) {
        List<Like> likes = likeRepository.findAllByUserId(userId);
        likes.forEach(this::deleteLike);
    }

}
