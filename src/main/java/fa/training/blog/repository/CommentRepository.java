package fa.training.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.training.blog.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
	List<CommentEntity> findAllByPost_Id(int postId);
	
	List<CommentEntity> findAllByStatusAndPost_Id(int status, int postId);

	@Modifying
	@Query(value = "update CommentEntity cmt set cmt.status=2 where cmt.id = :id")
	void approveComment(int id);
}
