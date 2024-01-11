package com.iocoder.yudao.module.operation;

import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.TrafficAccidentSaveReqVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OtherTest {

    @Test
    public void test() {
        LocalDate date = LocalDate.of(2023, 9, 13);
        LocalDate date2 = LocalDate.of(2024, 1, 11);
        long days = date.until(date2, ChronoUnit.DAYS);
        System.out.println(days);
    }
}
