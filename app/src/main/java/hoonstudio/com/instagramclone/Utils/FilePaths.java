package hoonstudio.com.instagramclone.Utils;

import android.os.Environment;

/**
 * Created by joon on 1/23/2018.
 */

public class FilePaths {
    //"storage/emulated/0"
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    public String CAMERA = ROOT_DIR + "DCIM/camera";
    public String PICTURES = ROOT_DIR + "/Pictures";

    public String FIREBASE_IMAGE_STORAGE = "photos/users/";

}
