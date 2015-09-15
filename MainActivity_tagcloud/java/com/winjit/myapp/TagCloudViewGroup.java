package com.winjit.myapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AmrutB on 25-06-2015.
 */
public class TagCloudViewGroup extends FrameLayout  implements GestureDetector.OnGestureListener {
    Context mContext;
    String[] tags = new String[]{"Lemon", "Orange", "Strawberry", "Plum", "Pear", "Pineapple", "Blackberry", "Watermelon","Apple","Banana","Mango","Watermelon","Jackfruit","Raspberry","Blueberry","Grapes"};
    private float scroll = 0;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float prevY;
    private String TAG = "TagCloudViewGroup";
    GestureDetector gestureDetector = new GestureDetector(this);

    public TagCloudViewGroup(Context context, AttributeSet st) {
        super(context, st);
        mContext = context;
        for (int i = 0; i < tags.length; i++) {
            final TextView mTv = new TextView(context);
            //final TextView mTv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.text_view, this, false);
            mTv.setText(tags[i]);
            mTv.setGravity(Gravity.CENTER);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //mTv.setBackgroundColor(Color.RED);
            mTv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    setScaleAnimation(v);
                    if (v instanceof TextView)
                    {
                        final TextView textView = (TextView) v;
                        textView.setTextColor(Color.RED);
                        textView.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(mContext, textView.getText() + "", Toast.LENGTH_SHORT).show();
                            }
                        }, 500);
                    }

                }
            });
            mTv.setShadowLayer(3, 1, 1, Color.BLACK);
            this.addView(mTv, params);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initChildren();
    }

   void initChildren() {
       float r = getHeight() / 3;
       for (int i = 0; i < getChildCount(); i++) {
           float t = i + scroll / getHeight();
           double theta=Math.PI * 2 * t / tags.length;
           float y = (float) (r * Math.cos(theta));    // parametric circle equation
           float z = (float) (r * Math.sin(theta));
           float textSize = (r + z) / r / 2 * 32 + 16;
           float alpha = (r + z) / r / 2 * 127 + 128;

           View view = getChildAt(i);
           int left = getWidth() / 2 - view.getWidth()/2;
           int top = (int) (getHeight() / 2 + y);
           int right = left + view.getWidth();
           int bottom = top + view.getHeight();
           int color=(int) (t*10);
           if (view instanceof TextView) {
               TextView textView = (TextView) view;
               textView.setTextSize(textSize);
               //textView.setAlpha(alpha);
               setAlphaAnimation(textView,alpha/255);
               textView.setTextColor(Color.WHITE);
               LayoutParams params = (LayoutParams) textView.getLayoutParams();
               params.setMargins(left,top,0,0);
               textView.setLayoutParams(params);
               Log.d(TAG,"text="+textView.getText() + "; z=" + z+"; scroll=" + scroll);
           }

           Log.d(TAG, "scroll=" + scroll + "; theta=" + theta + "; alpha="+alpha+"; y=" + y + "; z=" + z + "; r=" + r + "; i=" + i);
           //view.layout(left, top, right, bottom);
       }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll = scroll + changer;
                postInvalidate();
            }
        }, 1000);
    }
    private void setAlphaAnimation(View view, float alpha)
    {
        // TODO Auto-generated method stub
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(alpha, alpha);
        set.addAnimation(animation);
        set.setDuration(0);
        set.setFillAfter(true);
        view.setAnimation(set);
        set.start();
    }
    private void setScaleAnimation(View view)
    {
        // TODO Auto-generated method stub
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 5.0f, 1.0f, 5.0f, 50.0f, 50.0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(false);
        view.startAnimation(scaleAnimation);
    }

    int changer = -2;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            float  diff=event.getY() - prevY;
            scroll =scroll- diff*8;     // only one plane


            if (event.getY() != prevY) {
                if (event.getY() - prevY > 0) {
                    changer = 0 - Math.abs(changer);
                } else {
                    changer = Math.abs(changer);
                }
            }
            Log.d(TAG, "scroll=" + scroll + "; prevY=" + prevY + "; y=" + event.getY() + "; changer=" + changer);
        }
        prevY = event.getY();

        //removeAllViews();
        //refreshDrawableState();
        postInvalidate();
        return true;
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        layoutChildren();
        super.dispatchDraw(canvas);
        doScrolling();
    }

    private void doScrolling() {

    }



    private void layoutChildren() {
        initChildren();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (gestureDetector.onTouchEvent(event))
            return true;

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            forceFinished();
        }

        return super.dispatchTouchEvent(event);
    }

    private void forceFinished() {

    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
