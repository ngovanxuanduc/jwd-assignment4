package fa.training.blog.service;

import fa.training.blog.constant.PostStatus;
import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.response.HomeContentResp;
import fa.training.blog.payload.response.PostContentResp;
import fa.training.blog.repository.DBContext;
import fa.training.blog.utility.AuthenticationFacade;
import fa.training.blog.utility.DTOHelpler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
    @Autowired
    private DBContext dbContext;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public HomeContentResp getHomPublicContent() {
        //lay danh sach tag
//		List<String> tags =  dbContext.tagRepository.findAll().stream().map(TagEntity::getName).collect(Collectors.toList());
        List<String> tags = DTOHelpler.toTagNames(dbContext.tagRepository.findAll());
        //lay danh sach bai post
        List<PostContentResp> postContentResps = dbContext.postRepository.findAllByStatus(PostStatus.PUBLISHED)
                .stream().map(DTOHelpler::toDTO).collect(Collectors.toList());

        return HomeContentResp.builder()
                .tags(tags)
                .posts(postContentResps)
                .build();
    }

    public HomeContentResp getAllContentOfAuthor() {
        int authorId = authenticationFacade.getUserID();

        List<String> tags = DTOHelpler.toTagNames(dbContext.tagRepository.findAll());
        List<PostContentResp> postContentResps = dbContext.postRepository.findAllByAuthor_Id(authorId)
                .stream().map(DTOHelpler::toDTO).collect(Collectors.toList());
        return HomeContentResp.builder()
                .tags(tags)
                .posts(postContentResps)
                .build();
    }
//
//    private PostContentResp toDTO(PostEntity pE) {
//        PostContentResp resp = PostContentResp.builder()
//                .id(pE.getId())
//                .title(pE.getTitle())
//                .content(pE.getContent())
//                .createTime(pE.getCreateDate())
//                .updateTime(pE.getUpdateDate())
//                .authorName(pE.getAuthor().getUserName())
//                .tags(toTagNames(pE.getTags()))
//                .amountComment(pE.getComments().size())
//                .status(getStatus(pE.getStatus()))
//                .build();
//        return resp;
//    }
//
//    private List<String> toTagNames(Collection<TagEntity> tags) {
//        return tags.stream().map(TagEntity::getName).collect(Collectors.toList());
//    }
//
//    private String getStatus(int st) {
//        if (st == PostStatus.DRAFT) return "draft";
//        if (st == PostStatus.PUBLISHED) return "published";
//        if (st == PostStatus.OUTDATED) return "outdate";
//        return "";
//    }
}
