package hoonstudio.com.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import hoonstudio.com.instagramclone.Models.Photo;
import hoonstudio.com.instagramclone.R;
import hoonstudio.com.instagramclone.Utils.BottomNavigationViewHelper;
import hoonstudio.com.instagramclone.Utils.GridImageAdapter;
import hoonstudio.com.instagramclone.Utils.UniversalImageLoader;
import hoonstudio.com.instagramclone.ViewPostFragment;

/**
 * Created by joon on 12/15/2017.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileFragment.OnGridImageSelectedListener{
    private static final String TAG = "ProfileActivity";

    @Override
    public void onGridImageSelected(Photo photo, int activityNumber) {
        Log.d(TAG, "onGridImageSelected: Selected an image from gridview: " + photo.toString());

        ViewPostFragment fragment = new ViewPostFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.photo), photo);
        args.putInt(getString(R.string.calling_activity), activityNumber);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.view_post_fragment));
        transaction.commit();
    }

    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;
    private ImageView profilePhoto;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        init();
    }

    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

}
