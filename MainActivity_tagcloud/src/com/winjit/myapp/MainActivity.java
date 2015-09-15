package com.winjit.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;


import com.winjit.myapp.tagcloud.SampleTagCloud;


import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    RecyclerView rclvwList ;
    LinearLayoutManager layoutManager;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    RVListAdapter rvListAdapter;
    FragmentTransaction ft;
   	FragmentManager fm;
   	SampleTagCloud  sm;
    ArrayList<String> items=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sm = new SampleTagCloud
//				.newInstance(R.layout.news_fragment_en);
//		ft.replace(R.id.content, newsFragmentEN);
//		ft.addToBackStack(null);
//		ft.commit();
    	fm = this.getSupportFragmentManager();
		ft = fm.beginTransaction();

		sm	= new SampleTagCloud();
		ft.replace(R.id.content, sm);
		ft.addToBackStack(null);
		ft.commit();
		

//        layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        rclvwList= (RecyclerView)findViewById(R.id.xrclvwList);
//        rclvwList.setLayoutManager(layoutManager);
//
//        addItemsToList();
//
//        rvListAdapter=new RVListAdapter(items);
//        rclvwList.setAdapter(rvListAdapter);
//
//        rclvwList.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int visibleItemCount = rclvwList.getChildCount();
//                int totalItemCount = layoutManager.getItemCount();
//                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
//
//                if (loading) {
//                    if (totalItemCount > previousTotal) {
//                        loading = false;
//                        previousTotal = totalItemCount;
//                    }
//                }
//                if (!loading && (totalItemCount - visibleItemCount)
//                        <= (firstVisibleItem + visibleThreshold)) {
//                    // End has been reached
//
//                    Log.i("...", "end called");
//
//                    // Do something
//                    Toast.makeText(MainActivity.this,"end called",Toast.LENGTH_SHORT).show();
//                    loading = true;
//                }
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });

//        Intent intent=new Intent(MainActivity.this,SampleTagCloud.class);
//        startActivity(intent);
//        finish();
    }

    private void addItemsToList()
    {
        for (int i=0;i<100;i++)
        {
            items.add("item "+i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
