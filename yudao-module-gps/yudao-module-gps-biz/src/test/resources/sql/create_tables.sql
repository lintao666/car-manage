create table vehicle_gps_record
(
    id                  bigint auto_increment comment '编号' primary key,
    vehicle_id   bigint                              not null comment '车辆ID'
        primary key,
    dept_id      varchar(50)                         null comment '机构ID',
    devcode      varchar(20)                         not null comment '设备号',
    altitude     decimal(10, 2)                      null comment '海拔',
    obd_speed    int       default 0                 null comment 'OBD速度',
    gps_speed    int       default 0                 null comment 'GPS速度',
    direction    int       default 0                 null comment '方向',
    longitude    decimal(15, 8)                      null comment '经度',
    latitude     decimal(15, 8)                      null comment '纬度',
    gps_time     datetime                            null,
    machine_time datetime                            null comment '采集时间',
    update_time  timestamp default CURRENT_TIMESTAMP not null,
    acc          smallint                            null comment '1点火,0熄火',
    driver_id    varchar(64)                         null,
    onlinetime   datetime                            null,
    online       smallint                            null comment '是否在线',
    district     varchar(30)                         null comment '当前行政区域',
    obd_time     datetime                            null comment 'OBD设备 gps定位时间'
) comment '车辆行驶实时表';