package hoonstudio.com.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import hoonstudio.com.instagramclone.Models.Photo;
import hoonstudio.com.instagramclone.Utils.BottomNavigationViewHelper;
import hoonstudio.com.instagramclone.Utils.SquareImageView;
import hoonstudio.com.instagramclone.Utils.UniversalImageLoader;

/**
 * Created by joon on 1/29/2018.
 */

public class ViewPostFragment extends Fragment{

    private static final String TAG = "ViewPostFragment";

    public ViewPostFragment(){
        super();
        setArguments(new Bundle());
    }

    //variables
    private Photo mPhoto;
    private int mActivityNumber = 0;

    //widgets
    private SquareImageView mPostImage;
    private BottomNavigationViewEx bottomNavigationView;
    private TextView mBackLabel, mCaption, mUsername, mTimeStamp;
    private ImageView mBackArrow, mOptions, mHeartRed, mHeartBlack, mProfileImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);
        mPostImage = (SquareImageView) view.findViewById(R.id.post_image);
        bottomNavigationView = (BottomNavigationViewEx) view.findViewById(R.id.bottomNavigationViewBar);
        mBackLabel = (TextView) view.findViewById(R.id.tvBackLabel);
        mCaption = (TextView) view.findViewById(R.id.image_caption);
        mUsername = (TextView) view.findViewById(R.id.username);
        mTimeStamp = (TextView) view.findViewById(R.id.image_time_stamp);
        mBackArrow = (ImageView) view.findViewById(R.id.backArrow);
        mOptions = (ImageView) view.findViewById(R.id.ivOptions);
        mHeartRed = (ImageView) view.findViewById(R.id.ic_heart_red);
        mHeartBlack = (ImageView) view.findViewById(R.id.ic_heart_black);
        mProfileImage = (ImageView) view.findViewById(R.id.profile_photo);

        try{
            mPhoto = getPhotoFromBundle();
            UniversalImageLoader.setImage(mPhoto.getImage_path(), mPostImage, null, "");
            mActivityNumber = getActivityNumFromBundle();

        }catch(NullPointerException e){
            Log.e(TAG, "onCreateView: NullPointerException: " + e.getMessage());
        }

        setupBottomNavigationView();

        return view;
    }

    /**
     *  retrieve photo form incoming bundle from profileActivity interface
     * @return
     */
    private Photo getPhotoFromBundle(){
        Log.d(TAG, "getPhotoFromBundle: arguments: " + getArguments());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getParcelable(getString(R.string.photo));
        }else{
            return null;
        }
    }


    /**
     * retrieve activity number from incoming bundle
     * @return
     */
    private int getActivityNumFromBundle(){
        Log.d(TAG, "getActivityNumFromBundle: arguments: " + getArguments());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getInt(getString(R.string.activity_number));
        }else{
            Log.d(TAG, "getActivityNumFromBundle: return 0");
            return 0;
        }
    }

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(getActivity(), bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(mActivityNumber);
        menuItem.setChecked(true);
    }
}
