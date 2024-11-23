package hcmute.projectBackend2024.controller.statistic;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcmute.projectBackend2024.constant.AppConstants;
import hcmute.projectBackend2024.dto.statistic.StatisticResponse;
import hcmute.projectBackend2024.service.statistic.StatisticService;

@RestController
@RequestMapping("/api/stats")
@AllArgsConstructor
@CrossOrigin(AppConstants.FRONTEND_HOST)
public class StatisticController {

    private StatisticService statisticService;

    @GetMapping
    public ResponseEntity<StatisticResponse> getStatistic() {
        // TODO: [28-03-2023] Chưa rõ API này có lấy thống kê theo 7 ngày gần nhất?
        return ResponseEntity.status(HttpStatus.OK).body(statisticService.getStatistic());
    }

}
