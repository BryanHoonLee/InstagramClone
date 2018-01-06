package hoonstudio.com.instagramclone.Profile;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import hoonstudio.com.instagramclone.R;
import hoonstudio.com.instagramclone.Utils.SectionsStatePagerAdapter;

/**
 * Created by joon on 12/30/2017.
 */

public class AccountSettingActivity extends AppCompatActivity{

    private static final String TAG = "AccountSettingActivity";

    private Context mContext;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        mContext = AccountSettingActivity.this;
        Log.d(TAG, "onCreate: started.");
        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);

        setupSettingsList();

        setupFragments();

        //setup backarrow for navigating back to "ProfileActivity"
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
    }

    private void setupFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); //fragment 0
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); //fragment 1
    }

    //responsible for navigating to fragment
    private void setmViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setmViewPager: navigating to fragment #: " + fragmentNumber);
        //instantiates viewpager, sets the adapter and sets up fragments
        mViewPager.setAdapter(pagerAdapter);
        //navigates to chosen fragment
        mViewPager.setCurrentItem(fragmentNumber);
    }
    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: initializing Account Settings list");
        ListView listview = (ListView) findViewById(R.id.lvTextSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); //fragment 0
        options.add(getString(R.string.sign_out_fragment));  //fragment 1

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: navigating to fragment #: " + i);
                setmViewPager(i);
            }
        });
    }
}
