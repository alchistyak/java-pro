package tech.inno.task8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.inno.task8.entity.DayLimit;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayLimitRepository extends JpaRepository<DayLimit, Long> {
    Optional<DayLimit> findByUserId(Long userId);
    Optional<DayLimit> findByUserIdAndActive(Long userId, Boolean active);
    List<DayLimit> findAllByUserIdOrderByDateBegin(Long userId);

    @Modifying
    @Query("UPDATE DayLimit dlim SET dlim.active = FALSE WHERE coalesce(dlim.active, TRUE) = TRUE")
    void closeActiveLimits();
}
