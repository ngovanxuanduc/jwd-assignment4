package fa.training.blog.payload.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostContentResp {
	private int id;
	private String title;
	private String content;
	private Date createTime;
	private String authorName;
	private List<String> tags;
	private int amountComment;
	private Date updateTime;
	private String status;
}
