package com.zhangwei.domain;

import java.util.Objects;

public class FormData {
    private int gender;
    private boolean ownRealEstate;
    private boolean ownCar;
    private boolean ownWorkPhone;
    // 年收入
    private int annualIncome;
    private int childNum;
    private int age;
    private int worksYears;
    private int familySize;

    private String occupationType;
    private String houseType;
    private String educationStatus;
    private String marriageStatus;

    public FormData() {
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isOwnRealEstate() {
        return ownRealEstate;
    }

    public void setOwnRealEstate(boolean ownRealEstate) {
        this.ownRealEstate = ownRealEstate;
    }

    public boolean isOwnCar() {
        return ownCar;
    }

    public void setOwnCar(boolean ownCar) {
        this.ownCar = ownCar;
    }

    public boolean isOwnWorkPhone() {
        return ownWorkPhone;
    }

    public void setOwnWorkPhone(boolean ownWorkPhone) {
        this.ownWorkPhone = ownWorkPhone;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorksYears() {
        return worksYears;
    }

    public void setWorksYears(int worksYears) {
        this.worksYears = worksYears;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getEducationStatus() {
        return educationStatus;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormData formData = (FormData) o;
        return gender == formData.gender && ownRealEstate == formData.ownRealEstate && ownCar == formData.ownCar && ownWorkPhone == formData.ownWorkPhone && annualIncome == formData.annualIncome && childNum == formData.childNum && age == formData.age && worksYears == formData.worksYears && familySize == formData.familySize && Objects.equals(occupationType, formData.occupationType) && Objects.equals(houseType, formData.houseType) && Objects.equals(educationStatus, formData.educationStatus) && Objects.equals(marriageStatus, formData.marriageStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, ownRealEstate, ownCar, ownWorkPhone, annualIncome, childNum, age, worksYears, familySize, occupationType, houseType, educationStatus, marriageStatus);
    }

    @Override
    public String toString() {
        return "FormData{" +
                "gender=" + gender +
                ", ownRealEstate=" + ownRealEstate +
                ", ownCar=" + ownCar +
                ", ownWorkPhone=" + ownWorkPhone +
                ", annualIncome=" + annualIncome +
                ", childNum=" + childNum +
                ", age=" + age +
                ", worksYears=" + worksYears +
                ", familySize=" + familySize +
                ", occupationType='" + occupationType + '\'' +
                ", houseType='" + houseType + '\'' +
                ", educationStatus='" + educationStatus + '\'' +
                ", marriageStatus='" + marriageStatus + '\'' +
                '}';
    }
}
