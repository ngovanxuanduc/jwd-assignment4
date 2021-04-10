package fa.training.blog.service;

import fa.training.blog.constant.PostStatus;
import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.response.HomeContentResp;
import fa.training.blog.payload.response.PostContentResp;
import fa.training.blog.repository.DBContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
	@Autowired
	private DBContext dbContext;
	
	public HomeContentResp getHomPubliceContent() {
		//lay danh sach tag
//		List<String> tags =  dbContext.tagRepository.findAll().stream().map(TagEntity::getName).collect(Collectors.toList());
		List<String> tags =  toTagNames(dbContext.tagRepository.findAll());
		//lay danh sach bai post
		List<PostContentResp> postContentResps = dbContext.postRepository.findAllByStatus(PostStatus.PUBLISHED)
				.stream().map(this::toDTO).collect(Collectors.toList());

		return HomeContentResp.builder()
				.tags(tags)
				.posts(postContentResps)
				.build();
	}

	private PostContentResp toDTO(PostEntity pE){
		PostContentResp resp =  PostContentResp.builder()
				.title(pE.getTitle())
				.content(pE.getContent())
				.createTime(pE.getCreateDate())
				.updateTime(pE.getUpdateDate())
				.authorName(pE.getAuthor().getUserName())
				.tags(toTagNames(pE.getTags()))
				.amountComment(pE.getComments().size())
				.build();
		return resp;
	}

	private List<String> toTagNames(Collection<TagEntity> tags){
		return tags.stream().map(TagEntity::getName).collect(Collectors.toList());
	}

}
