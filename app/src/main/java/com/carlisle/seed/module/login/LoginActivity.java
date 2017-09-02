package com.carlisle.seed.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.carlisle.seed.R;
import com.carlisle.seed.module.base.CommonActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Creator      : carlisle
 * Date         : 01/09/2017
 * Description  :
 */

public class LoginActivity extends CommonActivity {
    private static final String TAG = "LoginActivity";

    private Toolbar toolbar;
    private EditText phoneNumberView;
    private TextView getVerifyView;
    private TextView loginView;
    private View loadingView;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        phoneNumberView = (EditText) findViewById(R.id.et_phone_number);
        getVerifyView = (TextView) findViewById(R.id.tv_get_verify_code);
        loginView = (TextView) findViewById(R.id.tv_login);
        loadingView = findViewById(R.id.rl_loading);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getVerifyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumberEffective()) {
                    requestVerifyCode();
                } else {
                    ToastUtils.showShort("请输入正确的手机号");
                }
            }
        });

        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumberEffective()) {
                    if (isVerifyCodeEffective()) {
                        requestLogin();
                    } else {
                        ToastUtils.showShort("请输入正确的验证码");
                    }
                } else {
                    ToastUtils.showShort("请输入正确的手机号");
                }
            }
        });
    }

    private void requestLogin() {
        loadingView.setVisibility(View.VISIBLE);

        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        loadingView.setVisibility(View.GONE);
                        ToastUtils.showShort("登录失败~");
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestVerifyCode() {
        startTimeCount();
    }

    private void startTimeCount() {
        final int countTime = 5;

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return countTime - aLong.intValue();
                    }
                })
                .take(countTime + 1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "requestLogin onSubscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        getVerifyView.setText(String.format("%s s", value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getVerifyView.setText("获取验证码");
                    }

                    @Override
                    public void onComplete() {
                        getVerifyView.setText("获取验证码");
                    }
                });
    }

    private boolean isPhoneNumberEffective() {
        String phoneNumber = phoneNumberView.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        } else {
            return RegexUtils.isMobileSimple(phoneNumber);
        }
    }

    private boolean isVerifyCodeEffective() {
        String verifyCode = getVerifyView.getText().toString().trim();
        return !TextUtils.isEmpty(verifyCode);
    }
}
