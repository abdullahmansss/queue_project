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

public class MMKSystemActivity extends AppCompatActivity
{
    EditText λField3, μField3, KField3;
    TextView resultField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_m_m_k_system );

        initViews();
    }

    private void initViews()
    {
        λField3 = findViewById ( R.id.λ3 );
        μField3 = findViewById ( R.id.μ3 );
        KField3 = findViewById ( R.id.K3 );

        resultField = findViewById ( R.id.result_field );

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId () == android.R.id.home)
            onBackPressed ();
        return super.onOptionsItemSelected ( item );
    }

    public void resultMMK(View view)
    {
        if(λField3.getText ().toString ().isEmpty () || Double.parseDouble ( λField3.getText ().toString () ) == 0)
        {
            λField3.requestFocus ();
            Toast.makeText ( this, "enter valid λ", Toast.LENGTH_SHORT ).show ();
            return;
        }

        if(μField3.getText ().toString ().isEmpty () || Double.parseDouble ( μField3.getText ().toString () ) == 0)
        {
            μField3.requestFocus ();
            Toast.makeText ( this, "enter valid μ", Toast.LENGTH_SHORT ).show ();
            return;
        }

        if(KField3.getText ().toString ().isEmpty () || Double.parseDouble ( KField3.getText ().toString () ) == 0)
        {
            KField3.requestFocus ();
            Toast.makeText ( this, "enter valid K", Toast.LENGTH_SHORT ).show ();
            return;
        }

        double λ = Double.parseDouble ( λField3.getText ().toString () );
        double μ = Double.parseDouble ( μField3.getText ().toString () );
        int K = Integer.parseInt ( KField3.getText ().toString () );

        double P = λ / μ;
        double Pk;
        double L;

        if(P != 1)
        {
            Pk = Math.pow ( P,K ) * ( ( 1 - P ) / ( 1 - Math.pow ( P,K+1 )));
            L = P * ( ( 1 - ( ( K + 1) * Math.pow ( P,K ) ) + (K * Math.pow ( P,K+1 )) ) / (( 1 - P ) * ( 1 - Math.pow ( P,K+1 ))));
        }else
        {
            Pk = 1 / ( K + 1 );
            L = K / 2;
        }

        double W = L / ( λ * ( 1 - Pk ) );
        double Wq = W - ( 1 / μ );
        double Lq = λ * ( 1 - Pk ) * Wq;

        resultField.setText (
                "L= " + L + '\n' +
                        "Lq= " + Lq + '\n' +
                        "W= " + W + '\n' +
                        "Wq= " + Wq);

        result ();
    }

    public void result()
    {
        λField3.setText ( "" );
        μField3.setText ( "" );
        KField3.setText ( "" );
    }
}