package hoonstudio.com.instagramclone.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import hoonstudio.com.instagramclone.R;

/**
 * Created by joon on 1/7/2018.
 */

public class RegistrationActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d(TAG, "onCreate: started");
    }
}
