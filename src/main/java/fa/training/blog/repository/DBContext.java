package fa.training.blog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBContext {
	@Autowired
	public PostRepository postRepository;
	
	@Autowired
	public TagRepository tagRepository;

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public CommentRepository commentRepository;
}
