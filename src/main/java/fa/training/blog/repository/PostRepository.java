package fa.training.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.blog.entity.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer>{
	List<PostEntity> findAllByStatus(int status);

	List<PostEntity> findAllByAuthor_Id(int authorId);

	PostEntity findByIdAndAuthor_Id(int postId, int authorId);
}
