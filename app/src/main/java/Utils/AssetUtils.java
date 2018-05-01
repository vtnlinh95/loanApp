package Utils;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 4/28/18.
 */

public class AssetUtils {

    public static String loadAssets(AssetManager assetManager, String fileName) {
        String content = "";

        try {
            InputStream stream = assetManager.open(fileName);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            content = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }
        return content;
    }
}
