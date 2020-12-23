package com.mustafa.queueingproject;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MM1SystemActivity extends AppCompatActivity
{
    EditText λField2, μField2;
    TextView resultField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_m_m1_system );

        initViews();

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId () == android.R.id.home)
            onBackPressed ();
        return super.onOptionsItemSelected ( item );
    }

    private void initViews()
    {
        λField2 = findViewById ( R.id.λ2 );
        μField2 = findViewById ( R.id.μ2 );

        resultField = findViewById ( R.id.result_field );
    }

    public void resultMM1(View view)
    {
        if(λField2.getText ().toString ().isEmpty () || Double.parseDouble ( λField2.getText ().toString () ) == 0)
        {
            λField2.requestFocus ();
            Toast.makeText ( this, "enter valid λ", Toast.LENGTH_SHORT ).show ();
            return;
        }

        if(μField2.getText ().toString ().isEmpty () || Double.parseDouble ( μField2.getText ().toString () ) == 0)
        {
            μField2.requestFocus ();
            Toast.makeText ( this, "enter valid μ", Toast.LENGTH_SHORT ).show ();
            return;
        }

        double λ = Double.parseDouble ( λField2.getText ().toString () );
        double μ = Double.parseDouble ( μField2.getText ().toString () );

        double L = λ / ( μ - λ );
        double Lq = ( λ * λ ) / ( μ * ( μ - λ ) );
        double W = L / λ;
        double Wq = Lq / λ;

        resultField.setText (
                "L= " + L + '\n' +
                        "Lq= " + Lq + '\n' +
                        "W= " + W + '\n' +
                        "Wq= " + Wq);

        result ();
    }

    private void result()
    {
        λField2.setText ( "" );
        μField2.setText ( "" );
    }
}