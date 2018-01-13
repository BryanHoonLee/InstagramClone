package hoonstudio.com.instagramclone.Utils;

/**
 * Created by joon on 1/12/2018.
 */

public class StringManipulation {
    public static String expandUsername(String username){
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username){
        return username.replace(" ", ".");
    }

}
