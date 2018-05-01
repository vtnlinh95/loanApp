package Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/30/18.
 */

public class Entity {
    @SerializedName("id")
    String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
