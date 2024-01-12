package com.iocoder.yudao.module.operation;

import cn.iocoder.yudao.framework.jackson.core.databind.LocalDateTimeDeserializer;
import cn.iocoder.yudao.framework.jackson.core.databind.LocalDateTimeSerializer;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.TrafficAccidentSaveReqVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JsonTest {
    public void test0() {
//        LocalDateSerializer.INSTANCE.serialize(LocalDate.now(),);
    }

    @Test
    public void testJsonTime() {


        //自编号 vehicleMask
        //认定书号  identificationRecordNum

        String content = "{\n" +
                "    \"accidentDate\": \"2024-01-06 01:46:35\",\n" +
                "    \"place\": \"违法地点testlintao2\",\n" +
                "    \"driverId\": 4565,\n" +
                "    \"vehicleId\": 21294,\n" +
                "    \"vehicleMask\": \"00002\",\n" +
                "    \"responsibility\": 1,\n" +
                "    \"identificationDept\": \"责任认定部门test\",\n" +
                "    \"identificationRecordNum\": \"认定书号sdfasdfasdfa\",\n" +
                "    \"accidentCategory\": \"事故类别testasdas\",\n" +
                "    \"level\": 3,\n" +
                "    \"vehicleCount\": 1,\n" +
                "    \"injuredPenson\": 1,\n" +
                "    \"deadPerson\": 0,\n" +
                "    \"accidentDesc\": \"事故简况\",\n" +
                "    \"handleRecord\": [\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-01-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-02-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-03-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-04-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项4\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-05-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项5\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"ownVehiclePay\": 11.11,\n" +
                "    \"otherVehiclePay\": 22.22,\n" +
                "    \"otherGoodsPay\": 33.33,\n" +
                "    \"vehicleTotalPay\": 66.66,\n" +
                "    \"hospitalPay\": 2.2,\n" +
                "    \"injuredDeadPay\": 33.3,\n" +
                "    \"otherPay\": 100.11,\n" +
                "    \"personTotalPay\": 135.61,\n" +
                "    \"totalPay\": 202.27,\n" +
                "    \"insuranceRecord\": [\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-01-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-02-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-03-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-04-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明4\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"insuranceTotal\": 4444.88,\n" +
                "    \"photos\": \"http://www.png\",\n" +
                "    \"handleProcess\": \"处理进度etesstes\",\n" +
                "    \"settlementDate\": \"2024-01-18\",\n" +
                "    \"injuredDeadDesc\": \"伤亡情况tsdfasdfas\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        SimpleModule javaTimeModule = new JavaTimeModule();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
                .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE)
                .addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE)
                .addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                // 新增 LocalDateTime 序列化、反序列化规则
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMapper.registerModule(simpleModule);
        // Additional customizations
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        TrafficAccidentSaveReqVO vo = null;
        try {
            vo = objectMapper.readValue(content, TrafficAccidentSaveReqVO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(vo);
    }


    @Test
    public void testJsonJavaTime() {
        //注意 accidentDate=1704478142000;

        String content = "{\n" +
//                "    \"accidentDate\": \"2024-01-06 01:46:35\",\n" +
                "    \"accidentDate\": 1704478142000,\n" +
                "    \"place\": \"违法地点testlintao2\",\n" +
                "    \"driverId\": 4565,\n" +
                "    \"vehicleId\": 21294,\n" +
                "    \"vehicleMask\": \"00002\",\n" +
                "    \"responsibility\": 1,\n" +
                "    \"identificationDept\": \"责任认定部门test\",\n" +
                "    \"identificationRecordNum\": \"认定书号sdfasdfasdfa\",\n" +
                "    \"accidentCategory\": \"事故类别testasdas\",\n" +
                "    \"level\": 3,\n" +
                "    \"vehicleCount\": 1,\n" +
                "    \"injuredPenson\": 1,\n" +
                "    \"deadPerson\": 0,\n" +
                "    \"accidentDesc\": \"事故简况\",\n" +
                "    \"handleRecord\": [\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-01-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-02-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-03-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-04-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项4\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-05-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项5\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"ownVehiclePay\": 11.11,\n" +
                "    \"otherVehiclePay\": 22.22,\n" +
                "    \"otherGoodsPay\": 33.33,\n" +
                "    \"vehicleTotalPay\": 66.66,\n" +
                "    \"hospitalPay\": 2.2,\n" +
                "    \"injuredDeadPay\": 33.3,\n" +
                "    \"otherPay\": 100.11,\n" +
                "    \"personTotalPay\": 135.61,\n" +
                "    \"totalPay\": 202.27,\n" +
                "    \"insuranceRecord\": [\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-01-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-02-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-03-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-04-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明4\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"insuranceTotal\": 4444.88,\n" +
                "    \"photos\": \"http://www.png\",\n" +
                "    \"handleProcess\": \"处理进度etesstes\",\n" +
                "    \"settlementDate\": \"2024-01-18\",\n" +
                "    \"injuredDeadDesc\": \"伤亡情况tsdfasdfas\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
                .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE)
                .addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE)
                .addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                // 新增 LocalDateTime 序列化、反序列化规则
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMapper.registerModule(simpleModule);
        // Additional customizations
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        TrafficAccidentSaveReqVO vo = null;
        try {
            vo = objectMapper.readValue(content, TrafficAccidentSaveReqVO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(vo);
    }

    @Test
    public void testJsonTime2() {


        //自编号 vehicleMask
        //认定书号  identificationRecordNum

        String content = "{\n" +
                "    \"accidentDate\": \"2024-01-06 01:46:35\",\n" +
                "    \"place\": \"违法地点testlintao2\",\n" +
                "    \"driverId\": 4565,\n" +
                "    \"vehicleId\": 21294,\n" +
                "    \"vehicleMask\": \"00002\",\n" +
                "    \"responsibility\": 1,\n" +
                "    \"identificationDept\": \"责任认定部门test\",\n" +
                "    \"identificationRecordNum\": \"认定书号sdfasdfasdfa\",\n" +
                "    \"accidentCategory\": \"事故类别testasdas\",\n" +
                "    \"level\": 3,\n" +
                "    \"vehicleCount\": 1,\n" +
                "    \"injuredPenson\": 1,\n" +
                "    \"deadPerson\": 0,\n" +
                "    \"accidentDesc\": \"事故简况\",\n" +
                "    \"handleRecord\": [\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-01-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-02-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-03-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-04-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项4\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"handleDate\": \"2024-05-06\",\n" +
                "            \"handleDesc\": \"事故救援及处理记录 事项5\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"ownVehiclePay\": 11.11,\n" +
                "    \"otherVehiclePay\": 22.22,\n" +
                "    \"otherGoodsPay\": 33.33,\n" +
                "    \"vehicleTotalPay\": 66.66,\n" +
                "    \"hospitalPay\": 2.2,\n" +
                "    \"injuredDeadPay\": 33.3,\n" +
                "    \"otherPay\": 100.11,\n" +
                "    \"personTotalPay\": 135.61,\n" +
                "    \"totalPay\": 202.27,\n" +
                "    \"insuranceRecord\": [\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-01-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-02-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-03-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明3\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"insuranceDate\": \"2024-04-29\",\n" +
                "            \"insurancePay\": \"1111.22\",\n" +
                "            \"insuranceDesc\": \"理赔说明4\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"insuranceTotal\": 4444.88,\n" +
                "    \"photos\": \"http://www.png\",\n" +
                "    \"handleProcess\": \"处理进度etesstes\",\n" +
                "    \"settlementDate\": \"2024-01-18\",\n" +
                "    \"injuredDeadDesc\": \"伤亡情况tsdfasdfas\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        SimpleModule javaTimeModule = new JavaTimeModule();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
                .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE)
                .addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE)
                .addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                // 新增 LocalDateTime 序列化、反序列化规则
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMapper.registerModule(simpleModule);
        // Additional customizations
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        TrafficAccidentSaveReqVO vo = null;
        try {
            vo = objectMapper.readValue(content, TrafficAccidentSaveReqVO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(vo);
    }


    @Test
    public void testJsonJavaTimeDes() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule
                .addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
                .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE)

                .addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE)
                .addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                // 新增 LocalDateTime 序列化、反序列化规则
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
        objectMapper.registerModule(simpleModule);
        // Additional customizations
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            String str = objectMapper.writeValueAsString(LocalDate.now());
            System.out.println(str);
            String str2 = objectMapper.writeValueAsString(LocalDateTime.now());
            System.out.println(str2);
            String str3 = objectMapper.writeValueAsString(LocalTime.now());
            System.out.println(str3);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testJsonJavaTimeDes2() {
        ObjectMapper objectMapper = new ObjectMapper();

//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule
//                .addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
//                .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE)
//
//                .addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE)
//                .addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE)
//                // 新增 LocalDateTime 序列化、反序列化规则
//                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
//                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
//        objectMapper.registerModule(simpleModule);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_DATE));
        objectMapper.registerModule(javaTimeModule);
        // Additional customizations
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            String str = objectMapper.writeValueAsString(LocalDate.now());
            System.out.println(str);
            String str2 = objectMapper.writeValueAsString(LocalDateTime.now());
            System.out.println(str2);
            String str3 = objectMapper.writeValueAsString(LocalTime.now());
            System.out.println(str3);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
