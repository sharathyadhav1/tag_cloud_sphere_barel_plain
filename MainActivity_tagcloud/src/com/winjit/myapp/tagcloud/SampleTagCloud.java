package com.winjit.myapp.tagcloud;

/**
 * Created by AmrutB on 25-06-2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.winjit.myapp.MyActivity;
import com.winjit.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * SampleTagCloud class: this is a sample program to show how the 3D Tag Cloud can be used. It Creates the activity and sets the ContentView to our TagCloudView class
 */
public class SampleTagCloud extends Fragment {
    LinearLayout linlayRoot;
    View view;
    protected TagCloudView.OnTagClickListener onTagClickListener = new TagCloudView.OnTagClickListener() {

        @Override
        public void onTagClick(View view, Tag tag) {
            // TODO Auto-generated method stub
            Toast.makeText(getActivity(), tag.getText() + "", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    };
    
    
    

    @Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	 view = inflater.inflate(R.layout.tag_cloud_fragment, container,false);
//		return view;
//	}
//	public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        // Step0: to get a full-screen View:
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Step1: get screen resolution:
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        // setContentView(mTagCloudView);
        linlayRoot = (LinearLayout)view. findViewById(R.id.xlinlayRoot);
        // Step3: create our TagCloudview and set it as the content of our
        // MainActivity

        linlayRoot.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // Step2: create the required TagList:
                List<Tag> myTagList = createTags();

                mTagCloudView = new TagCloudView(getActivity(), linlayRoot.getWidth(), linlayRoot.getHeight(), myTagList);
                linlayRoot.addView(mTagCloudView);
                mTagCloudView.requestFocus();
                mTagCloudView.setFocusableInTouchMode(true);
                mTagCloudView.setOnTagClickListener(onTagClickListener);

            }
        });

        view.findViewById(R.id.ximgvwFlat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagCloudView.setTagCloudType(TagCloudView.TagCloudType.FLAT);
            }
        });
        view.findViewById(R.id.ximgvwSphere).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagCloudView.setTagCloudType(TagCloudView.TagCloudType.SPHERE);
            }
        });
        view.findViewById(R.id.ximgvwBarrel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagCloudView.setTagCloudType(TagCloudView.TagCloudType.BARREL);
            }
        });
        view.findViewById(R.id.ximgvwBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });
		return view;

        //Intent intent=new Intent(SampleTagCloud.this,MyActivity.class);
        //startActivity(intent);

    }
    public void restoreActionBar(CharSequence mTitle)
    {
        //drawerArrowDrawable = new DrawerArrowDrawable(getResources());
        //drawerArrowDrawable.setStrokeColor(getResources().getColor(R.color.white));
        //drawerArrowDrawable.setParameter(1.0f);
        //drawerArrowDrawable.setFlip(true);

//        ActionBar actionBar = getActivity().getActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transblack)));
//        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle(mTitle);
        //actionBar.setIcon(drawerArrowDrawable);
        //actionBar.setHomeAsUpIndicator(drawerArrowDrawable);

    }
  

    private List<Tag> createTags() {
        // create the list of tags with popularity values and related url
        List<Tag> tempList = new ArrayList<Tag>();

        tempList.add(new Tag("Google", 8, "http://www.google.com")); // 1,4,7,...
        // assumed
        // values
        // for
        // popularity
        //tempList.add(new Tag("Chetan", 8, "www.chetan.com"));
        //tempList.add(new Tag("Amrut", 8, "www.amrut.com"));
        tempList.add(new Tag("Yahoo", 8, "www.yahoo.com"));
        tempList.add(new Tag("CNN", 8, "www.cnn.com"));
        tempList.add(new Tag("MSNBC", 8, "www.msnbc.com"));
        tempList.add(new Tag("CNBC", 8, "www.CNBC.com"));
        tempList.add(new Tag("Facebook", 8, "www.facebook.com"));
        tempList.add(new Tag("Youtube", 8, "www.youtube.com"));
        tempList.add(new Tag("BlogSpot", 8, "www.blogspot.com"));
        tempList.add(new Tag("Bing", 8, "www.bing.com"));
        tempList.add(new Tag("Wikipedia", 8, "www.wikipedia.com"));
        tempList.add(new Tag("Twitter", 8, "www.twitter.com"));
        tempList.add(new Tag("Msn", 8, "www.msn.com"));
      
        return tempList;
    }

    private TagCloudView mTagCloudView;
}
