package com.zhangwei.domain;

import java.util.Objects;

public class FormData {
    //female -> 0 male->1
    private boolean gender;
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

    public FormData(boolean gender, boolean ownRealEstate, boolean ownCar, boolean ownWorkPhone, int childNum, int age,
                    int worksYears, int familySize, int annualIncome, String occupationType, String houseType,
                    String educationStatus, String marriageStatus) {
        this.gender = gender;
        this.ownRealEstate = ownRealEstate;
        this.ownCar = ownCar;
        this.ownWorkPhone = ownWorkPhone;
        this.childNum = childNum;
        this.age = age;
        this.worksYears = worksYears;
        this.familySize = familySize;
        this.annualIncome = annualIncome;
        this.occupationType = occupationType;
        this.houseType = houseType;
        this.educationStatus = educationStatus;
        this.marriageStatus = marriageStatus;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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

    public int getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
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
        return gender == formData.gender && ownRealEstate == formData.ownRealEstate && ownCar == formData.ownCar && ownWorkPhone == formData.ownWorkPhone && childNum == formData.childNum && age == formData.age && worksYears == formData.worksYears && familySize == formData.familySize && annualIncome == formData.annualIncome && Objects.equals(occupationType, formData.occupationType) && Objects.equals(houseType, formData.houseType) && Objects.equals(educationStatus, formData.educationStatus) && Objects.equals(marriageStatus, formData.marriageStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, ownRealEstate, ownCar, ownWorkPhone, childNum, age, worksYears, familySize, annualIncome, occupationType, houseType, educationStatus, marriageStatus);
    }

    @Override
    public String toString() {
        return "FormData{" +
                "gender=" + gender +
                ", ownRealEstate=" + ownRealEstate +
                ", ownCar=" + ownCar +
                ", ownWorkPhone=" + ownWorkPhone +
                ", childNum=" + childNum +
                ", age=" + age +
                ", worksYears=" + worksYears +
                ", familySize=" + familySize +
                ", annualIncome=" + annualIncome +
                ", occupationType='" + occupationType + '\'' +
                ", houseType='" + houseType + '\'' +
                ", educationStatus='" + educationStatus + '\'' +
                ", marriageStatus='" + marriageStatus + '\'' +
                '}';
    }
}
