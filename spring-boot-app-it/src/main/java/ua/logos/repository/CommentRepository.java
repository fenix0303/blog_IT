package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    boolean existsById(Long id);
}
