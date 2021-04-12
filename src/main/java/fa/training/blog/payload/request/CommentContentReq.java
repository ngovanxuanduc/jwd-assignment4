package fa.training.blog.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentContentReq {
    private int postId;

    @NotBlank(message = "Author Not Blank")
    private String author;

    @NotBlank(message = "Email Not Blank")
    private String email;

    @NotBlank(message = "Content Not Blank")
    private String content;
}
