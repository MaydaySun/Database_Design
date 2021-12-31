drop table if exists test_record;
drop table if exists log;
drop table if exists takes;
drop table if exists course_to_dept;
drop table if exists course;
drop table if exists manager;
drop table if exists instructor;
drop table if exists employee;
drop table if exists department;


create table department
(
    dept_id   varchar(8)  not null,
    dept_name varchar(20) not null, -- 部门编号和名称不能为空
    primary key (dept_id)
) DEFAULT CHARSET = utf8;
ALTER TABLE department
    CONVERT TO CHARACTER SET utf8;

create table employee
(
    id              varchar(11)     not null,
    name            varchar(10)     not null, -- 员工的id和姓名不能为空
    gender          varchar(1)      check ( gender in (null, '男', '女') ),-- 性别可以不填，或者填入'男'、'女'
    age             int,
    date_employed   TIMESTAMP       default current_timestamp, -- 默认认为该员工是今天才录取的
    city            varchar(4),
    phone_number    numeric(11, 0),
    email           varchar(50),
    dept_id         varchar(8)      not null,
    primary key (id),
    index (name),
    index (dept_id),-- 为name和department_id建立索引
    foreign key (dept_id) references department (dept_id)
        on delete cascade
        on update cascade
) DEFAULT CHARSET = utf8;
ALTER TABLE employee
    CONVERT TO CHARACTER SET utf8; -- 修改字段编码为UTF8，防止中文乱码

create table manager
(
    employee_id     varchar(11)     not null,
    name            varchar(10)     not null, -- name其实是一个冗余属性，但可以便于获取一个部门主管的名字
    dept_id         varchar(8)      not null, -- 部门主管对应的部门id不能为空
    primary key (employee_id),
    unique (dept_id), -- 一个部门只能有一个部门主管，所以department_id需要唯一性约束
    foreign key (employee_id) references employee (id)
        on delete cascade -- 当employee信息变动，manager对应的信息也级联改动
        on update cascade
) DEFAULT CHARSET = utf8;
ALTER TABLE manager
    CONVERT TO CHARACTER SET utf8;

create table instructor
(
    employee_id     varchar(11)     not null,
    name            varchar(10)     not null,
    date_registered TIMESTAMP   default current_timestamp,
    primary key (employee_id),
    foreign key (employee_id) references employee (id)
        on delete cascade
        on update cascade
) DEFAULT CHARSET = utf8;
ALTER TABLE instructor
    CONVERT TO CHARACTER SET utf8;

create table course
(
    course_id       varchar(7)      not null,
    title           varchar(20)     not null, -- 课程编号和名称不能为空
    type            varchar(10)      default '尚无分类', -- 课程类型
    content         varchar(50)     default '尚无描述',
    instructor_id   varchar(11)     not null, -- 一门课只有一个教员教授，以instructor_id作为course的属性即可
    primary key (course_id),
    foreign key (instructor_id) references instructor (employee_id)
        on delete cascade
        on update cascade
) DEFAULT CHARSET = utf8;
ALTER TABLE course
    CONVERT TO CHARACTER SET utf8;

create table test_record -- 单次测试的记录
(
    record_id       bigint          not null    auto_increment, -- 测试记录可能会很多，使用bigint
    course_id       varchar(7)      not null,
    employee_id     varchar(11)     not null,
    score           int             not null    check ( score >= 0 and score <= 100 ), -- 当某次测试的score达到60需要更新takes表中对应记录为已通过
    PRIMARY KEY (record_id)
) DEFAULT CHARSET = utf8;
ALTER TABLE test_record
    CONVERT TO CHARACTER SET utf8;

create table log
(
    log_id      int             not null    auto_increment,
    username    varchar(10)     not null    default 'admin', -- 操作者的姓名，若不是员工操作，默认值为admin操作
    content   varchar(30)     not null,
    time        TIMESTAMP       default current_timestamp, -- 日志时间戳
    primary key (log_id)
) DEFAULT CHARSET = utf8;
ALTER TABLE log
    CONVERT TO CHARACTER SET utf8;

create table takes
(
    employee_id     varchar(11)     not null,
    course_id       varchar(7)      not null,
    completed       int             not null    default 0   check ( completed in (0, 1)),-- 结课状态，默认为未结课。
    -- 结课状态针对的是(employee_id, course_id)即一个员工是否学习完了该课程，而不是该课程是否已经结束并注销
    -- 直到管理员将此课程从course表中移除，代表该课程已注销
    is_passed       int             check ( is_passed in  (null, 0, 1)), -- 培训的通过状态
    primary key (employee_id, course_id),
    foreign key (employee_id) references employee (id)
        on delete cascade
        on update cascade,
    foreign key (course_id) references course(course_id)
        on delete cascade
        on update cascade
) DEFAULT CHARSET = utf8;
ALTER TABLE takes
    CONVERT TO CHARACTER SET utf8;

create table course_to_dept -- 课程与部门的关联表
(
    course_id       varchar(7)      not null,
    dept_id         varchar(8)      not null,
    required        varchar(2)      not null    check ( required in ('必修', '选修')),
    primary key (course_id, dept_id),
    foreign key (course_id) references course (course_id)
        on delete cascade
        on update cascade,
    foreign key (dept_id) references department (dept_id)
        on delete cascade
        on update cascade
) DEFAULT CHARSET = utf8;
ALTER TABLE course_to_dept
    CONVERT TO CHARACTER SET utf8;


-- 向department表插入初始信息
insert into department
values (1, '总裁办公室');
insert into department
values (2, '研究院');
insert into department
values (3, '人力资源部门');
insert into department
values (4, '产品研发部门');
insert into department
values (5, '营销部门');
insert into department
values (6, '推广部门');
insert into department
values (7, '人事管理部门');
insert into department
values (8, '测试部门');
insert into department
values (9, '策划部门');
insert into department
values (10, '技术部门');

-- 向employee表插入初始信息
insert into employee
values ('10231106002', '李耀', '男', 27, current_timestamp, '上海', '11111111111', 'example@xx.com', 8);
insert into employee
values ('10231106003', '王鑫', '男', 32, current_timestamp, '杭州', '11111111111', 'example@xx.com', 10);
insert into employee
values ('10231106004', '王倩', '女', 35, current_timestamp, '南京', '11111111111', 'example@xx.com', 9);
insert into employee
values ('10231106005', '卢潇', '男', 27, current_timestamp, '深圳', '11111111111', 'example@xx.com', 10);
insert into employee
values ('10231106138', '宋雪', '女', 25, current_timestamp, '北京', '11111111111', 'example@xx.com', 8);
insert into employee
values ('10231106124', '蒋玥', '女', 29, current_timestamp, '西安', '11111111111', 'example@xx.com', 10);

insert into instructor
values ('10231106138', '宋雪', current_timestamp);
insert into instructor
values ('10231106124', '蒋玥', current_timestamp);

insert into manager
values ('10231106004', '王倩', 9);
insert into manager
values ('10231106005', '卢潇', 10);

insert into course
values ('35142', '数据库设计', '开发', 'xxxxxxx', '10231106124');
insert into course
values ('35155', '软件测试', '测试', 'xxxxxxx', '10231106138');

insert into course_to_dept
values ('35142', 10, '必修');
insert into course_to_dept
values ('35155', 8, '必修');

insert into takes -- 根据必修课程初始化takes表
values('10231106002', '35155', 0, null);
insert into takes
values('10231106003', '35142', 0, null);


