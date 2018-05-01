package Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/30/18.
 */

public class UserEntity extends Entity{
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("national_id_number")
    private String nationalID;
    @SerializedName("monthly_income")
    private double monthlyIncome;
    @SerializedName("province")
    private String province;

    public UserEntity(String id, String phoneNumber, String fullName, String nationalID, String monthlyIncome, String province) {
        super(id);
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.nationalID = nationalID;
        this.monthlyIncome = getConvertMonthlyIncome(monthlyIncome);
        this.province = province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = getConvertMonthlyIncome(monthlyIncome);
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getConvertMonthlyIncome(String value) {
        double val = Double.parseDouble(value);
        if (val < 3000000) {
            return 1;
        }
        if (val <= 10000000) {
            return 3000001;
        }
        return 10000001;
    }
}
