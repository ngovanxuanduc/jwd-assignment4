package fa.training.blog.payload.response;

import java.util.List;

import fa.training.blog.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentContentResp {
	private PostContentResp post;
	
	private List<CommentEntity> comments; 
}
