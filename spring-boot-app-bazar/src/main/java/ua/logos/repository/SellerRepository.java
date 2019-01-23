package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.SellerEntity;
@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {

    boolean existsById(Long Id);

}
