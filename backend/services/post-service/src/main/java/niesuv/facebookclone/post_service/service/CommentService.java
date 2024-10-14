package niesuv.facebookclone.post_service.service;


import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.post_service.dto.CreateCommentDTO;
import niesuv.facebookclone.post_service.entity.Comment;
import niesuv.facebookclone.post_service.entity.Post;
import niesuv.facebookclone.post_service.exception.CreateCommentException;
import niesuv.facebookclone.post_service.exception.PostIdNotExists;
import niesuv.facebookclone.post_service.exception.UnValidInput;
import niesuv.facebookclone.post_service.exception.UserNotExistException;
import niesuv.facebookclone.post_service.http.UserFeignClient;
import niesuv.facebookclone.post_service.repository.CommentRepository;
import niesuv.facebookclone.post_service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserFeignClient userFeignClient;

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostService postService;

    public void clearAllCommentsByUserID(UUID id) {
        List<Comment> comments = commentRepository.findAllByUserID(id);
        comments.forEach(cmt -> deleteComment(cmt.getId()));
    }

    public void deleteComment(UUID cmtID) {
        Comment comment = commentRepository.findById(cmtID)
                .orElseThrow(() -> new UnValidInput("Comment not found"));
        Comment parentComment = comment.getReplyTo();
        parentComment.setTotalReplies(parentComment.getTotalReplies() - 1);
        commentRepository.delete(comment);
        commentRepository.save(parentComment);
    }

    public UUID addComment(CreateCommentDTO dto) {
        if (!userFeignClient.exists(dto.userId())) {
            throw new UserNotExistException("UserId does not belongs to any users");
        }


        Comment comment = null;
        if (dto.replyToId() != null) {
            Optional<Comment> optionalComment = commentRepository.findById(dto.replyToId());
            if (optionalComment.isPresent()) {
                comment = optionalComment.get();
            } else
                throw new UnValidInput("Comment ID dont belongs to any comment");
        }

        Optional<Post> opPost = postRepository.findById(dto.postId());
        if (opPost.isPresent()) {
            var post = opPost.get();
            var id = commentRepository.save(toComment(dto)).getId();
            if (id == null)
                throw new CreateCommentException("Cannot create comment!");
            else {
                post.setTotalComments(post.getTotalComments() + 1);
                postRepository.save(post);
                if (comment != null) {
                    comment.setTotalReplies(comment.getTotalReplies() + 1);
                    commentRepository.save(comment);
                }
                return id;
            }
        } else {
            throw new PostIdNotExists("PostId does not exist");
        }

    }


    private static Comment toComment(CreateCommentDTO dto) {
        return Comment.builder()
                .post(Post.builder().id(dto.postId()).build())
                .replyTo(dto.replyToId() == null ? null : Comment.builder().id(dto.replyToId()).build())
                .userId(dto.userId())
                .content(dto.content())
                .build();
    }
}
