package Entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.admin.myapplication.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/28/18.
 */

public class LoanEntity extends BaseObservable {
    @SerializedName("min_amount")
    private Double minAmount;
    @SerializedName("max_amount")
    private Double maxAmount;
    @SerializedName("min_tenor")
    private int minTenor;
    @SerializedName("max_tenor")
    private int maxTenor;
    @SerializedName("interest_rate")
    private Double interestRate;
    @SerializedName("bank")
    private BankEntity bank;

    public LoanEntity() {}

    public LoanEntity(Double minAmount, Double maxAmount, int minTenor, int maxTenor, Double interestRate, BankEntity bank) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.minTenor = minTenor;
        this.maxTenor = maxTenor;
        this.interestRate = interestRate;
        this.bank = bank;
    }

    @Bindable
    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
        notifyPropertyChanged(BR.minAmount);
    }

    @Bindable
    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
        notifyPropertyChanged(BR.maxAmount);
    }

    @Bindable
    public int getMinTenor() {
        return minTenor;
    }

    public void setMinTenor(int minTenor) {
        this.minTenor = minTenor;
        notifyPropertyChanged(BR.minTenor);
    }

    @Bindable
    public int getMaxTenor() {
        return maxTenor;
    }

    public void setMaxTenor(int maxTenor) {
        this.maxTenor = maxTenor;
        notifyPropertyChanged(BR.maxTenor);
    }

    @Bindable
    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
        notifyPropertyChanged(BR.interestRate);
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }
}
