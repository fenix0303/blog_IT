package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.PostEntity;
import ua.logos.entity.UserEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
    boolean existsById(Long id);

}
