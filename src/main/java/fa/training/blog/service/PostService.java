package fa.training.blog.service;

import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.repository.DBContext;
import fa.training.blog.utility.AuthenticationFacade;
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

    public PostEntity createPost(PostContentReq pCR){
        //Lay ra list Tag, roi kiem tra trong db chua co thi luu vao
        List<String> tags = Arrays.stream(pCR.getTags().split(",")).map(String::trim).collect(Collectors.toList());
        List<TagEntity> tagList = getTagListInDBAndSaveIfNotExist(tags);
        //luu post vao db ne

        //doan nay se xoa khi ma bat cau hinh dang nhap

        PostEntity postEntity = PostEntity.builder()
                .title(pCR.getTitle())
                .content(pCR.getContent())
                .status(pCR.getStatus())
                .tags(tagList)
                //ti dang nhap roi bat lai thang nay
//                .author(authenticationFacade.getUserPrinciple().getUser())
                .author(dbContext.userRepository.findByUserName("admin"))
                .build();
//        authenticationFacade.getUserPrinciple().getUser().getId();
        return dbContext.postRepository.save(postEntity);
    }


    private List<TagEntity> getTagListInDBAndSaveIfNotExist(List<String> tags){
        List<TagEntity> tagList = new ArrayList<>();
        TagEntity tagEntity = null;
        for (String tagName: tags) {
            tagEntity = dbContext.tagRepository.findByNameIgnoreCase(tagName);
            if (tagEntity == null){
                //luu vao db ne
                tagEntity = dbContext.tagRepository.save(new TagEntity(tagName));
            }
            tagList.add(tagEntity);
        }
        return tagList;
    }
}
