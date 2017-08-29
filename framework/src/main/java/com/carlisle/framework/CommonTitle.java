package com.carlisle.framework;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class CommonTitle extends RelativeLayout {
    private static final float DEFAULT_SUB_TITLE_ALPHA = 0.5F;

    private Drawable leftActionImage;
    private Drawable rightActionImage;
    private TextView leftActionTextView;
    private ImageView leftActionImageView;
    private TextView rightActionTextView;
    private ImageView rightActionImageView;
    private RelativeLayout titleRootView;
    private TextView titleView;
    private TextView subTitleView;
    private ViewGroup leftAction;
    private ViewGroup rightAction;

    private String title;
    private String subTitle;
    private String leftActionText;
    private String rightActionText;
    private Drawable titleBackgroundDrawable;
    private OnActionClickListener listener = new OnActionClickListener() {
    };

    public CommonTitle(Context context) {
        super(context);
        init(null, 0);
    }

    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CommonTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        initAttrs(attrs, defStyleAttr);
        initView();
        refreshUI();
    }

    private void initAttrs(AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CommonTitle, defStyleAttr, 0);
        try {
            leftActionText = a.getString(R.styleable.CommonTitle_leftActionText);
            leftActionImage = a.getDrawable(R.styleable.CommonTitle_leftActionImage);
            rightActionText = a.getString(R.styleable.CommonTitle_rightActionText);
            rightActionImage = a.getDrawable(R.styleable.CommonTitle_rightActionImage);
            titleBackgroundDrawable = a.getDrawable(R.styleable.CommonTitle_titleBackground);
            title = a.getString(R.styleable.CommonTitle_barTitle);
            subTitle = a.getString(R.styleable.CommonTitle_barSubTitle);
        } finally {
            a.recycle();
        }
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_common_title, this);
        this.titleRootView = (RelativeLayout) findViewById(R.id.rl_common_title);
        this.leftActionTextView = (TextView) findViewById(R.id.tv_left_action);
        this.leftActionImageView = (ImageView) findViewById(R.id.iv_left_action);
        this.rightActionTextView = (TextView) findViewById(R.id.tv_right_action);
        this.rightActionImageView = (ImageView) findViewById(R.id.iv_right_action);
        this.titleView = (TextView) findViewById(R.id.tv_title);
        this.subTitleView = (TextView) findViewById(R.id.tv_sub_title);
        this.leftAction = (ViewGroup) findViewById(R.id.rl_left_action);
        this.rightAction = (ViewGroup) findViewById(R.id.rl_right_action);
        this.subTitleView.setAlpha(DEFAULT_SUB_TITLE_ALPHA);
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        super.setBackgroundColor(color);
        this.titleRootView.setBackgroundColor(color);
    }

    public void refreshUI() {
        if (titleBackgroundDrawable != null) {
            titleRootView.setBackgroundDrawable(titleBackgroundDrawable);
        }
        refreshTitleText();
        refreshLeftContainer();
        refreshRightContainer();
    }

    private void refreshTitleText() {
        if (!TextUtils.isEmpty(title)) {
            titleView.setText(title);
        }

        if (!TextUtils.isEmpty(subTitle)) {
            subTitleView.setText(subTitle);
            subTitleView.setVisibility(VISIBLE);
        } else {
            subTitleView.setVisibility(GONE);
        }
    }

    private void refreshLeftContainer() {
        boolean leftVisible = !TextUtils.isEmpty(leftActionText) || leftActionImage != null;

        if (!leftVisible) {
            leftAction.setVisibility(View.INVISIBLE);
            return;
        }
        leftAction.setVisibility(View.VISIBLE);
        leftAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickedLeftAction();
                }
            }
        });

        if (!TextUtils.isEmpty(leftActionText)) {
            leftActionTextView.setVisibility(View.VISIBLE);
            leftActionTextView.setText(leftActionText);
        } else {
            leftActionTextView.setVisibility(View.GONE);
        }

        if (leftActionImage != null) {
            leftActionImageView.setImageDrawable(leftActionImage);
            leftActionImageView.setVisibility(VISIBLE);
        } else {
            leftActionImageView.setVisibility(GONE);
        }
    }

    private void refreshRightContainer() {
        boolean rightVisible = !TextUtils.isEmpty(rightActionText) || rightActionImage != null;

        if (!rightVisible) {
            rightAction.setVisibility(View.INVISIBLE);
            return;
        }

        rightAction.setVisibility(View.VISIBLE);
        rightAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickedRightAction();
                }
            }
        });

        if (!TextUtils.isEmpty(rightActionText)) {
            rightActionTextView.setVisibility(View.VISIBLE);
            rightActionTextView.setText(rightActionText);
        } else {
            rightActionTextView.setVisibility(View.GONE);
        }

        if (rightActionImage != null) {
            rightActionImageView.setImageDrawable(rightActionImage);
            rightActionImageView.setVisibility(VISIBLE);
        } else {
            rightActionImageView.setVisibility(GONE);
        }
    }

    public void setTitle(String title) {
        this.title = title;
        refreshTitleText();
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        refreshTitleText();
    }

    public void setOnActionListener(OnActionClickListener listener) {
        this.listener = listener;
    }

    public void setLeftActionImageView(Drawable leftActionImage) {
        this.leftActionImage = leftActionImage;
        refreshLeftContainer();
    }

    public void setRightActionImageView(Drawable rightActionImage) {
        this.rightActionImage = rightActionImage;
        refreshRightContainer();
    }

    public void setLeftTextAction(OnClickListener listener) {
        leftActionTextView.setOnClickListener(listener);
    }

    public void setLeftTextVisible(int visible) {
        leftActionTextView.setVisibility(visible);
    }

    public void setRightActionText(String rightActionText) {
        this.rightActionText = rightActionText;
        refreshRightContainer();
    }

    public void setLeftActionText(String leftActionText) {
        this.leftActionText = leftActionText;
        refreshLeftContainer();
    }

    public void setTextColorOfTitle(@ColorInt int color) {
        this.titleView.setTextColor(color);
    }

    public void setTextColorOfSubTitle(@ColorInt int color) {
        this.subTitleView.setTextColor(color);
    }

    public void setTextColorOfSubTitle(@ColorInt int color, float alpha) {
        this.subTitleView.setTextColor(color);
        this.subTitleView.setAlpha(alpha);
    }

    public void setRightActionTextColor(@ColorInt int color) {
        this.rightActionTextView.setTextColor(color);
    }

    public static class OnActionClickListener {
        public void onClickedLeftAction() {
        }

        public void onClickedRightAction() {
        }
    }
}
