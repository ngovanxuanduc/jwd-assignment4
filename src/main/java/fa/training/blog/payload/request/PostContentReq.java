package fa.training.blog.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostContentReq {
	private int id;
	
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
