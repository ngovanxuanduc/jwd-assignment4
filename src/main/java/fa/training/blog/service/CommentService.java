package fa.training.blog.service;

import java.util.Optional;

import fa.training.blog.constant.CommentStatus;
import fa.training.blog.payload.request.CommentContentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.blog.entity.CommentEntity;
import fa.training.blog.entity.PostEntity;
import fa.training.blog.payload.response.CommentContentResp;
import fa.training.blog.repository.DBContext;
import fa.training.blog.utility.DTOHelpler;

import javax.transaction.Transactional;

@Service
public class CommentService {
	@Autowired
	private DBContext dbContext;

	public CommentContentResp getAllCommentInPost(int postId) {
		// tim thang post tuong ung, khong co thi thoi
		Optional<PostEntity> postEntity = dbContext.postRepository.findById(postId);
		if (postEntity.isPresent()) {
			return CommentContentResp.builder().post(DTOHelpler.toDTO(postEntity.get()))
					.comments(dbContext.commentRepository.findAllByPost_Id(postId)).build();
		}
		return null;
	}

	public CommentContentResp getComment(int postId) {
		// tim thang post tuong ung, khong co thi thoi
		Optional<PostEntity> postEntity = dbContext.postRepository.findById(postId);
		if (postEntity.isPresent()) {
			return CommentContentResp.builder().post(DTOHelpler.toDTO(postEntity.get()))
					.comments(dbContext.commentRepository.findAllByStatusAndPost_Id(CommentStatus.APPROVE, postId)).build();
		}
		return null;
	}

	public CommentEntity addComment(CommentContentReq contentReq){
		CommentEntity commentEntity = CommentEntity.builder()
				.author(contentReq.getAuthor())
				.status(CommentStatus.UN_APPROVE)
				.content(contentReq.getContent())
				.email(contentReq.getEmail())
				.post(dbContext.postRepository.findById(contentReq.getPostId()).get())
				.build();
		return dbContext.commentRepository.save(commentEntity);
	}

	@Transactional
	public void approveComment(int id){
		dbContext.commentRepository.approveComment(id);
	}

	@Transactional
	public void deleteComment(int id){
		dbContext.commentRepository.deleteById(id);
	}
}
