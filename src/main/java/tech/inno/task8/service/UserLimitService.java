package tech.inno.task8.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.inno.task8.dto.UserLimitRequest;
import tech.inno.task8.dto.UserLimitResponse;
import tech.inno.task8.entity.UserLimit;
import tech.inno.task8.repository.UserLimitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLimitService {
    private final UserLimitRepository userLimitRepository;

    public UserLimitService(UserLimitRepository userLimitRepository) {
        this.userLimitRepository = userLimitRepository;
    }

    // Получение индивидуального лимита пользователя
    public UserLimit getUserLimit(Long userId) {
        Optional<UserLimit> userLimitOptional = userLimitRepository.findByUserId(userId);
        if (userLimitOptional.isPresent()) {
            return userLimitOptional.get();
        }
        return null;
    }

    // Запись индивидуального лимита пользователя в БД
    @Transactional
    public UserLimitResponse setupUserLimit(UserLimitRequest userLimitRequest) {
        UserLimit userLimit = new UserLimit();
        Optional<UserLimit> userLimitOptional = userLimitRepository.findByUserId(userLimitRequest.userId());
        if (userLimitOptional.isPresent()) {
            userLimit = userLimitOptional.get();
            userLimit.setUserLimit(userLimitRequest.userLimit());
        } else {
            userLimit.setUserId(userLimitRequest.userId());
            userLimit.setUserLimit(userLimitRequest.userLimit());
        }
        userLimit = userLimitRepository.save(userLimit);
        return new UserLimitResponse(userLimit.getId(), userLimit.getUserId(), userLimit.getUserLimit());
    }

    // Обработка массива лимитов пользователей
    @Transactional
    public List<UserLimitResponse> setupUserLimits(List<UserLimitRequest> userLimitRequestList) {
        List<UserLimitResponse> userLimitResponseList = new ArrayList<>();
        for (UserLimitRequest userLimitRequest : userLimitRequestList) {
            userLimitResponseList.add(setupUserLimit(userLimitRequest));
        }
        return userLimitResponseList;
    }
}
