package hoonstudio.com.instagramclone.Home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import hoonstudio.com.instagramclone.R;



/**
 * Created by joon on 12/15/2017.
 */

public class CameraFragment extends Fragment {
    private static final String TAG = "CameraFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        return view;
    }
}