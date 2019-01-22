package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    boolean existsById(Long id);
    boolean existsByNicknameIgnoreCase (String nickname);
}
