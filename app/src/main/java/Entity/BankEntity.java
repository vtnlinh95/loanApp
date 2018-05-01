package Entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.myapplication.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/28/18.
 */

public class BankEntity extends BaseObservable {
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logoUrl;

    public BankEntity() {

    }

    public BankEntity(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @BindingAdapter({"android:logoUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
