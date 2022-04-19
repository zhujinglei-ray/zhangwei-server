-- create database uksnacks;
\connect zhangweidb;


--     private int gender;
--     private boolean ownRealEstate;
--     private boolean ownCar;
--     private boolean ownWorkPhone;
--     // 年收入
--     private int annualIncome;
--     private int childNum;
--     private int age;
--     private int worksYears;
--     private int familySize;
--
--     private String occupationType;
--     private String houseType;
--     private String educationStatus;
--     private String marriageStatus;
DROP TABLE if EXISTS user_record;
CREATE TABLE user_record
(
    id              SERIAL PRIMARY KEY NOT NULL,
    gender          INTEGER,
    ownRealEstate   boolean,
    ownCar          boolean,
    ownWorkPhone    boolean,
    annualIncome    INTEGER,
    childNum        INTEGER,
    age             INTEGER,
    worksYears      INTEGER,
    familySize      INTEGER,
    occupationType  VARCHAR(255),
    houseType       VARCHAR(255),
    educationStatus VARCHAR(255),
    marriageStatus  VARCHAR(255)
);

