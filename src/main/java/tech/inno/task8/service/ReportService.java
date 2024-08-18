package tech.inno.task8.service;

import org.springframework.stereotype.Service;
import tech.inno.task8.dto.PaymentResponse;
import tech.inno.task8.dto.ReportForDayResponse;
import tech.inno.task8.dto.ReportRequest;
import tech.inno.task8.dto.ReportResponse;
import tech.inno.task8.entity.DayLimit;
import tech.inno.task8.entity.Payment;
import tech.inno.task8.repository.DayLimitRepository;
import tech.inno.task8.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final DayLimitRepository dayLimitRepository;
    private final PaymentRepository paymentRepository;

    public ReportService(DayLimitRepository dayLimitRepository, PaymentRepository paymentRepository) {
        this.dayLimitRepository = dayLimitRepository;
        this.paymentRepository = paymentRepository;
    }

    public ReportResponse reportByUserId(Long userId) {
        List<ReportForDayResponse> reportForDayResponseList = new ArrayList<>();
        List<DayLimit> dayLimitList = dayLimitRepository.findAllByUserIdOrderByDateBegin(userId);
        for (DayLimit dayLimit : dayLimitList) {
            List<PaymentResponse> paymentResponseList = new ArrayList<>();
            List<Payment> paymentList = paymentRepository.findByDayLimitIdOrderByPaymentDateTime(dayLimit.getId());
            for (Payment payment : paymentList) {
                paymentResponseList.add(new PaymentResponse(payment.getTransactionId(), payment.getPaymentDateTime(), payment.getPaymentAmount(), payment.getPaymentStatus(), null));
            }
            ReportForDayResponse reportForDayResponse = new ReportForDayResponse(dayLimit.getId(), dayLimit.getUnusedLimit(), dayLimit.getDateBegin(), dayLimit.getActive(), paymentResponseList);
            reportForDayResponseList.add(new ReportForDayResponse(dayLimit.getId(), dayLimit.getUnusedLimit(), dayLimit.getDateBegin(), dayLimit.getActive(), paymentResponseList));
        }
        return new ReportResponse(userId, reportForDayResponseList);
    }
}
