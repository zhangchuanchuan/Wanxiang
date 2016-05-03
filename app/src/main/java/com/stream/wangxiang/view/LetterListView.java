package com.stream.wangxiang.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.stream.wangxiang.utils.DimenUtils;

/**
 *
 * Created by 张川川 on 2016/5/3.
 */
public class LetterListView extends View {
    private LetterListView.OnTouchingLetterChangedListener mTouchingListener;
    private String[] mLetters;
    private int mLastChoose = -1;
    private Paint mPaint;
    boolean mDrawBackground;
    private TextView mLetterView;
    private int mHeight;

    public LetterListView(Context context) {
        super(context);
        this.init();
    }

    public LetterListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public LetterListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    @TargetApi(21)
    public LetterListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    private void init() {
        this.mPaint = new Paint();
        this.mLetters = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    }

    public void setLetters(String[] letters) {
        this.mLetters = letters;
    }

    public void setLetterView(TextView v) {
        this.mLetterView = v;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.mDrawBackground) {
            canvas.drawColor(Color.parseColor("#40000000"));
        }

        int height = this.getHeight();
        int width = this.getWidth();
        int singleHeight = height / this.mLetters.length;

        for(int i = 0; i < this.mLetters.length; ++i) {
            this.mPaint.setColor(Color.parseColor("#666666"));
            this.mPaint.setTypeface(Typeface.DEFAULT);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextSize((float) DimenUtils.dip2px(15.0F));
            if(i == this.mLastChoose) {
                this.mPaint.setColor(Color.parseColor("#fb5329"));
                this.mPaint.setFakeBoldText(true);
            }

            float xPos = (float)(width / 2) - this.mPaint.measureText(this.mLetters[i]) / 2.0F;
            float yPos = (float)(singleHeight * i + singleHeight);
            canvas.drawText(this.mLetters[i], xPos, yPos, this.mPaint);
            this.mPaint.reset();
        }

    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mHeight = this.getHeight();
        if(this.mHeight <= 0) {
            this.mHeight = DimenUtils.dip2px(380.0F);
        }

    }

    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int c = Math.round(event.getY() / (float)this.mHeight * (float)this.mLetters.length);
        if(c < 0) {
            c = 0;
        } else if(c >= this.mLetters.length) {
            c = this.mLetters.length - 1;
        }

        switch(event.getAction()) {
            case 0:
                this.mDrawBackground = false;
                if(this.mLetterView != null) {
                    this.mLetterView.setText(this.mLetters[c]);
                    this.mLetterView.setVisibility(View.VISIBLE);
                }

                if(this.mLastChoose != c) {
                    if(this.mTouchingListener != null) {
                        this.mTouchingListener.onTouchingLetterChanged(this.mLetters[c]);
                    }

                    this.mLastChoose = c;
                    this.invalidate();
                }
                break;
            case 1:
                this.mDrawBackground = false;
                this.mLastChoose = -1;
                this.invalidate();
            case 3:
                if(this.mLetterView != null) {
                    this.mLetterView.setVisibility(View.GONE);
                }
                break;
            case 2:
                if(this.mLastChoose != c) {
                    if(this.mLetterView != null) {
                        this.mLetterView.setText(this.mLetters[c]);
                    }

                    if(this.mTouchingListener != null) {
                        this.mTouchingListener.onTouchingLetterChanged(this.mLetters[c]);
                    }

                    this.mLastChoose = c;
                    this.invalidate();
                }
        }

        return true;
    }

    public void setOnTouchingLetterChangedListener(LetterListView.OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.mTouchingListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String var1);
    }
}