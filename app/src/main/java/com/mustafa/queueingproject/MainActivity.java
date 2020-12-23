package com.mustafa.queueingproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

    }

    public void DD1(View view)
    {
        intent ( DD1SystemActivity.class );
    }

    public void MM1(View view)
    {
        intent ( MM1SystemActivity.class );
    }

    public void MMK(View view)
    {
        intent ( MMKSystemActivity.class );
    }

    public void intent(Class activity)
    {
        Intent intent = new Intent (getApplicationContext (), activity);
        startActivity ( intent );
    }

}