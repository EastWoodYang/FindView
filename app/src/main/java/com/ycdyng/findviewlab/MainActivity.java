package com.ycdyng.findviewlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ycdyng.findview.ViewHelper;

public class MainActivity extends AppCompatActivity {

    ViewHelper mViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewHelper = ViewHelper.bindView(this, R.layout.activity_main);

        mViewHelper.setImageResource(R.id.avatar_image_view, R.mipmap.ic_launcher_round)
                .setText(R.id.title_text_view, "FindView")
                .setText(R.id.sub_title_text_view, "helper you find view.");

    }

}