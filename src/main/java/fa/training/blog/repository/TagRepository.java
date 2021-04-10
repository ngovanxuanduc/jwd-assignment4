package fa.training.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.blog.entity.TagEntity;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer>{
    List<TagEntity> findAllByNameIgnoreCaseIn(String[] names);

    TagEntity findByNameIgnoreCase(String name);
}
