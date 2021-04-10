package fa.training.blog.payload.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PostContentReq {

    @NotBlank(message = "Title Not Blank")
    private String title;

    @NotBlank(message = "Content Not Blank")
    private String content;

    @NotBlank(message = "Tags Not Blank")
    private String tags;

    @Min(1)
    @Max(3)
    private int status;
}
