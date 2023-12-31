CREATE TABLE IF NOT EXISTS "business_device" (
                                                 "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
                                                 "device_id" varchar NOT NULL,
                                                 "device_type" int NOT NULL,
                                                 "bound_car_number" varchar,
                                                 "status" int NOT NULL,
                                                 "creator" varchar DEFAULT '',
                                                 "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                 "updater" varchar DEFAULT '',
                                                 "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                 "deleted" bit NOT NULL DEFAULT FALSE,
                                                 "tenant_id" bigint NOT NULL,
                                                 PRIMARY KEY ("id")
    ) COMMENT '设备';


CREATE TABLE IF NOT EXISTS "business_driver" (
    "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "company_id" bigint NOT NULL,
    "name" varchar NOT NULL,
    "id_number" varchar NOT NULL,
    "car_number" varchar,
    "phone_number" varchar NOT NULL,
    "emergency_telephone" varchar,
    "residential_address" varchar,
    "driver_license_number" varchar,
    "driving_class" varchar,
    "driver_license_start_time" varchar,
    "driver_license_expiration_time" varchar,
    "head_portrait" varchar,
    "attachment" varchar NOT NULL,
    "status" int NOT NULL,
    "creator" varchar DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" varchar DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '司机';

CREATE TABLE IF NOT EXISTS "business_vehicle" (
    "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "company_id" bigint NOT NULL,
    "car_number" varchar NOT NULL,
    "brand" varchar NOT NULL,
    "vehicle_model" int NOT NULL,
    "energy_type" int NOT NULL,
    "vehicle_type" int NOT NULL,
    "vin" varchar NOT NULL,
    "engine_number" varchar NOT NULL,
    "device_id_list" varchar NOT NULL,
    "driver_id_list" varchar NOT NULL,
    "current_state" int NOT NULL,
    "attachment" varchar NOT NULL,
    "status" int NOT NULL,
    "creator" varchar DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" varchar DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    "tenant_id" bigint NOT NULL,
    PRIMARY KEY ("id")
) COMMENT '车辆';