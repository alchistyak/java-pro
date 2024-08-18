package tech.inno.task8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.inno.task8.entity.UserLimit;

import java.util.Optional;

@Repository
public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {
    Optional<UserLimit> findByUserId(Long userId);
}
