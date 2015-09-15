package com.winjit.myapp.tagcloud;

/**
 * Created by AmrutB on 25-06-2015.
 */

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.winjit.myapp.MyActivity;
import com.winjit.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * SampleTagCloud class: this is a sample program to show how the 3D Tag Cloud can be used. It Creates the activity and sets the ContentView to our TagCloudView class
 */
public class SampleTagCloud extends AppCompatActivity {
    LinearLayout linlayRoot;
    protected TagCloudView.OnTagClickListener onTagClickListener = new TagCloudView.OnTagClickListener() {

        @Override
        public void onTagClick(View view, Tag tag) {
            // TODO Auto-generated method stub
            Toast.makeText(SampleTagCloud.this, tag.getText() + "", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Step0: to get a full-screen View:
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Step1: get screen resolution:
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        // setContentView(mTagCloudView);
        linlayRoot = (LinearLayout) findViewById(R.id.xlinlayRoot);
        // Step3: create our TagCloudview and set it as the content of our
        // MainActivity

        linlayRoot.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                // Step2: create the required TagList:
                List<Tag> myTagList = createTags();

                mTagCloudView = new TagCloudView(SampleTagCloud.this, linlayRoot.getWidth(), linlayRoot.getHeight(), myTagList);
                linlayRoot.addView(mTagCloudView);
                mTagCloudView.requestFocus();
                mTagCloudView.setFocusableInTouchMode(true);
                mTagCloudView.setOnTagClickListener(onTagClickListener);

            }
        });

        findViewById(R.id.ximgvwFlat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagCloudView.setTagCloudType(TagCloudView.TagCloudType.FLAT);
            }
        });
        findViewById(R.id.ximgvwSphere).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagCloudView.setTagCloudType(TagCloudView.TagCloudType.SPHERE);
            }
        });
        findViewById(R.id.ximgvwBarrel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTagCloudView.setTagCloudType(TagCloudView.TagCloudType.BARREL);
            }
        });
        findViewById(R.id.ximgvwBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Intent intent=new Intent(SampleTagCloud.this,MyActivity.class);
        //startActivity(intent);

    }
    public void restoreActionBar(CharSequence mTitle)
    {
        //drawerArrowDrawable = new DrawerArrowDrawable(getResources());
        //drawerArrowDrawable.setStrokeColor(getResources().getColor(R.color.white));
        //drawerArrowDrawable.setParameter(1.0f);
        //drawerArrowDrawable.setFlip(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transblack)));
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(mTitle);
        //actionBar.setIcon(drawerArrowDrawable);
        //actionBar.setHomeAsUpIndicator(drawerArrowDrawable);

    }
    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
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
        tempList.add(new Tag("Amazon", 8, "www.amazon.com"));
        tempList.add(new Tag("Ebay", 8, "www.ebay.com"));
        tempList.add(new Tag("LinkedIn", 8, "www.linkedin.com"));
        tempList.add(new Tag("Live", 8, "www.live.com"));
        tempList.add(new Tag("Microsoft", 8, "www.microsoft.com"));
        tempList.add(new Tag("Flicker", 8, "www.flicker.com"));
        tempList.add(new Tag("Apple", 8, "www.apple.com"));
        tempList.add(new Tag("Paypal", 8, "www.paypal.com"));
        // tempList.add(new Tag("Craigslist", 7, "www.craigslist.com"));
        tempList.add(new Tag("Imdb", 8, "www.imdb.com"));
        tempList.add(new Tag("Ask", 8, "www.ask.com"));
        // tempList.add(new Tag("Weibo", 1, "www.weibo.com"));
        tempList.add(new Tag("Tagin!", 8, "http://scyp.idrc.ocad.ca/projects/tagin"));
        // tempList.add(new Tag("Shiftehfar", 8, "www.shiftehfar.org"));
        // tempList.add(new Tag("Soso", 5, "www.google.com"));
        // tempList.add(new Tag("XVideos", 3, "www.xvideos.com"));
        tempList.add(new Tag("BBC", 8, "www.bbc.co.uk"));
        return tempList;
    }

    private TagCloudView mTagCloudView;
}
