package tech.inno.task8.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.inno.task8.dto.UserLimitRequest;
import tech.inno.task8.dto.UserLimitResponse;
import tech.inno.task8.service.UserLimitService;

import java.util.List;

// Установка индивидуальных лимитов пользователей
// Формат запроса:
//    [
//      {
//        "userId" : 4,
//            "userLimit": 5000.00
//      },
//      {
//        "userId" : 3,
//            "userLimit": 4000.00
//      },
//      {
//        "userId" : 103,
//            "userLimit": 999.00
//      }
//    ]

@RestController
@RequestMapping("/api/v1")
public class UserLimitController {
    private final UserLimitService userLimitService;

    public UserLimitController(UserLimitService userLimitService) {
        this.userLimitService = userLimitService;
    }

    @PostMapping("/setup/")
    public List<UserLimitResponse> setupUserLimits(@Valid @RequestBody List<UserLimitRequest> userLimitRequestList) {
        return userLimitService.setupUserLimits(userLimitRequestList);
    }
}
