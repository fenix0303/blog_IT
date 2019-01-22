package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    boolean existsById(Long id);
}
