package fa.training.blog.utility;

import fa.training.blog.constant.PostStatus;
import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.payload.response.PostContentResp;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DTOHelpler {
    public static PostContentResp toDTO(PostEntity pE) {
        PostContentResp resp = PostContentResp.builder()
                .id(pE.getId())
                .title(pE.getTitle())
                .content(pE.getContent())
                .createTime(pE.getCreateDate())
                .updateTime(pE.getUpdateDate())
                .authorName(pE.getAuthor().getUserName())
                .tags(toTagNames(pE.getTags()))
                .amountComment(pE.getComments().size())
                .status(getStatus(pE.getStatus()))
                .build();
        return resp;
    }

    public static List<String> toTagNames(Collection<TagEntity> tags) {
        return tags.stream().map(TagEntity::getName).collect(Collectors.toList());
    }
    public static String getStatus(int st) {
        if (st == PostStatus.DRAFT) return "draft";
        if (st == PostStatus.PUBLISHED) return "published";
        if (st == PostStatus.OUTDATED) return "outdate";
        return "";
    }

    public static PostContentReq toDTOReq(PostEntity pe){
        PostContentReq.builder()
                .title(pe.getTitle())
                .content(pe.getContent())
                .status(pe.getStatus())
                .tags(pe.getTags().stream().map(TagEntity::getName).collect(Collectors.joining(", ")))
                .build();
        return null;
    }
}
