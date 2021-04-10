package fa.training.blog.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeContentResp {
	private List<PostContentResp> posts;
	
	private List<String> tags;
}
