package tech.inno.task8.service;

import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.inno.task8.config.property.DefaultSettings;
import tech.inno.task8.entity.DayLimit;
import tech.inno.task8.entity.UserLimit;
import tech.inno.task8.repository.DayLimitRepository;
import tech.inno.task8.repository.UserLimitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DayLimitService {
    private final DayLimitRepository dayLimitRepository;
    private final UserLimitRepository userLimitRepository;
    private final DefaultSettings defaultSettings;

    public DayLimitService(DayLimitRepository dayLimitRepository, UserLimitRepository userLimitRepository, DefaultSettings defaultSettings) {
        this.dayLimitRepository = dayLimitRepository;
        this.userLimitRepository = userLimitRepository;
        this.defaultSettings = defaultSettings;
    }

    // Задание по расписанию добавляет в таблицу дневных лимитов индивидуальные лимиты пользователей
    // Пользователям, которым не задан индивидуальный лимит, дневной лимит по умолчанию устанавливается при первом запросе на выполнение платежа
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void closeActiveLimitsAndSetupNewLimits() {
        System.out.println("minUserId: " + defaultSettings.getMinUserId());
        System.out.println("maxUserId: " + defaultSettings.getMaxUserId());
        System.out.println("defaultLimit: " + defaultSettings.getDefaultLimit());
        dayLimitRepository.closeActiveLimits();
        List<UserLimit> userLimitList = userLimitRepository.findAll();
        for (UserLimit userLimit : userLimitList) {
            Long userId = userLimit.getUserId();
            if (userId >= defaultSettings.getMinUserId() && userId <= defaultSettings.getMaxUserId()) {
                DayLimit dayLimit = new DayLimit();
                dayLimit.setUserId(userId);
                dayLimit.setUnusedLimit(userLimit.getUserLimit());
                dayLimit.setActive(true);
                dayLimit.setDateBegin(LocalDateTime.now());
                dayLimitRepository.save(dayLimit);
            }
        }
    }
}
