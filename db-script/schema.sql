create database IF NOT EXISTS patient_appointments;

--  create user table--
create table if not exists patient_appointments.user_info
(
    id       int auto_increment
        primary key,
    email    varchar(255) null,
    name     varchar(255) null,
    password varchar(255) null,
    role     varchar(255) null
);
-- create patient table --
create table if not exists patient_appointments.patient
(
    patient_id   bigint       not null
        primary key,
    gender       varchar(255) null,
    patient_age  varchar(255) null,
    patient_name varchar(255) null
);

-- create appointments table --
create table if not exists patient_appointments.appointments
(
    id                         bigint       not null
        primary key,
    appointment_date           datetime(6)  null,
    appointment_type           varchar(255) null,
    patient_reservation_number int          null,
    patient_patient_id         bigint       null,
    constraint FK1t7lhls1g3a5rvrai9kc76f7l
        foreign key (patient_patient_id) references patient_appointments.patient (patient_id)
);

create table if not exists patient_appointments.appointments_history
(
    id                  bigint       not null
        primary key,
    appointment_id      bigint       null,
    appointment_status  varchar(255) null,
    cancellation_reason varchar(255) null,
    patient_id          bigint       null
);




