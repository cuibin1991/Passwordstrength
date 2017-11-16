package com.example.cuibin.passwordstrength;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cuibin.passwordstrength.component.AlertDialogBuilder;
import com.example.cuibin.passwordstrength.component.PasswordStrength;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , TextWatcher {

    private Toolbar toolbar;
    private EditText tvOldPwd;
    private EditText tvNewPwd;
    private EditText tvSurePwd;
    private Button btnSure;
    private Button btnCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        initview();
        initDate();
        tvNewPwd.addTextChangedListener(this);
    }

    private void initview() {
        tvOldPwd = (EditText) findViewById(R.id.change_pwd_old_pwd);
        tvNewPwd = (EditText) findViewById(R.id.change_pwd_new_pwd);
        tvSurePwd = (EditText) findViewById(R.id.change_pwd_sure_pwd);
        btnSure = (Button) findViewById(R.id.change_pwd_sure);
        btnCancle = (Button) findViewById(R.id.change_pwd_cancle);
        btnSure.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
    }

    private void initActionBar() {
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initDate() {
        //设置标题  自带的标题没有居中显示的操作
        toolbar.setTitle("修改密码");
        setSupportActionBar(toolbar);
        //设置标题的颜色
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        //设置标题的位置
        toolbar.setTitleMargin(53,16,16,55);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_pwd_sure:
                changePwd();
                break;
            case R.id.change_pwd_cancle:
                this.finish();
                break;
            default:
                break;
        }
    }

    private void changePwd() {
        String oldPwd = tvOldPwd.getText().toString().trim();
        String newPwd = tvNewPwd.getText().toString().trim();
        String surePwd = tvSurePwd.getText().toString().trim();

        // 旧密码不能为空
        if (TextUtils.isEmpty(oldPwd)) {
            showMessageDialog(R.string.change_pwd_old_pwd_can_not_null);
            return;
        }
        // 新密码不能为空
        if (TextUtils.isEmpty(newPwd)) {
            showMessageDialog(R.string.change_pwd_old_pwd_can_not_null);
            return;
        }

        PasswordStrength str = PasswordStrength.calculateStrength(newPwd);
        if(str.getText(this).equals("低")){
            showMessageDialog(R.string.passwordnew_security_low);
            return;
        }
        // 两次密码不一致
        if (!newPwd.equals(surePwd)) {
            showMessageDialog(R.string.change_pwd_not_same_twice);
            return;
        }
    }

    private void showMessageDialog(int msgId) {
        AlertDialogBuilder.newBuilder(this)
                .setTitle(getResources().getString(R.string.dialog_title))
                .setMessage(getResources().getString(msgId))
                .setPositiveButton(
                        getResources().getString(R.string.dialog_sure), null)
                .show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        updatePasswordStrengthView(editable.toString());
    }

    private void updatePasswordStrengthView(String password) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar_changepassword);
        TextView strengthView = (TextView) findViewById(R.id.password_strength_change);

        if (TextView.VISIBLE != strengthView.getVisibility())
            return;

        if (password.isEmpty()) {
            strengthView.setText("");
            progressBar.setProgress(0);
            return;
        }

        PasswordStrength str = PasswordStrength.calculateStrength(password);
        strengthView.setText(str.getText(this));
        strengthView.setTextColor(str.getColor());

        progressBar.getProgressDrawable().setColorFilter(str.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        if (str.getText(this).equals("低")) {
            progressBar.setProgress(33);
        } else if (str.getText(this).equals("中")) {
            progressBar.setProgress(66);
        } else if (str.getText(this).equals("高")) {
            progressBar.setProgress(100);
        } else {
            progressBar.setProgress(0);
        }
    }

}


