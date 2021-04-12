package fa.training.blog.service;

import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.repository.DBContext;
import fa.training.blog.utility.AuthenticationFacade;
import fa.training.blog.utility.DTOHelpler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private DBContext dbContext;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public PostEntity createPost(PostContentReq pCR) {
        //Lay ra list Tag, roi kiem tra trong db chua co thi luu vao
        List<String> tags = Arrays.stream(pCR.getTags().split(",")).map(String::trim).collect(Collectors.toList());
        List<TagEntity> tagList = getTagListInDBAndSaveIfNotExist(tags);
        //luu post vao db ne
        PostEntity postEntity = PostEntity.builder()
        		.id(pCR.getId())
                .title(pCR.getTitle())
                .content(pCR.getContent())
                .status(pCR.getStatus())
                .tags(tagList)
                .author(authenticationFacade.getUserPrinciple().getUser())
                .build();
        return dbContext.postRepository.save(postEntity);
    }

    private List<TagEntity> getTagListInDBAndSaveIfNotExist(List<String> tags) {
        List<TagEntity> tagList = new ArrayList<>();
        TagEntity tagEntity = null;
        for (String tagName : tags) {
            tagEntity = dbContext.tagRepository.findByNameIgnoreCase(tagName);
            if (tagEntity == null) {
                //luu vao db ne
                tagEntity = dbContext.tagRepository.save(new TagEntity(tagName));
            }
            tagList.add(tagEntity);
        }
        return tagList;
    }

    public boolean deletePost(int postId) {
        //kiem tra post co thuoc id duoc xoa hay ko
        int authorId = authenticationFacade.getUserID();
//        PostEntity post = dbContext.postRepository.findByIdAndAuthor_Id(postId, authorId);
        PostEntity post = getPostByIdAndAuthorID(postId, authorId);
        if (post == null) {
            return false;
        }
        dbContext.postRepository.delete(post);
        return true;
    }

    public PostEntity getPostByIdAndAuthorID(int postId, int authorId) {
        return dbContext.postRepository.findByIdAndAuthor_Id(postId, authorId);
    }

    public PostContentReq getPostContentReqByIdAndAuthorID(int postId) {
        int authorId = authenticationFacade.getUserID();
        PostEntity postEntity = getPostByIdAndAuthorID(postId, authorId);
        if (postEntity != null){
            return DTOHelpler.toDTOReq(getPostByIdAndAuthorID(postId, authorId));
        }
        return new PostContentReq();
    }

}
