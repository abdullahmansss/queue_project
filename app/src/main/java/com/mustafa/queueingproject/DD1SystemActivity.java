package com.mustafa.queueingproject;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DD1SystemActivity extends AppCompatActivity
{
    EditText λField1, μField1, KField1, MField1;
    TextView resultField;
    Boolean isM;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_d_d1_system );

        initViews();
        isM();

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId () == android.R.id.home)
            onBackPressed ();
        return super.onOptionsItemSelected ( item );
    }

    public void isM()
    {
        λField1.addTextChangedListener ( new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!μField1.getText ().toString ().isEmpty () && !λField1.getText ().toString ().isEmpty () && Double.parseDouble ( μField1.getText ().toString () ) <= Double.parseDouble ( s.toString () ))
                {
                    isM = true;
                    MField1.setVisibility ( View.VISIBLE );
                }
                else
                    {
                        isM = false;
                        MField1.setVisibility ( View.GONE );
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

        μField1.addTextChangedListener ( new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!s.equals("."))
                    if(!λField1.getText ().toString ().isEmpty () && !μField1.getText ().toString ().isEmpty () && Double.parseDouble ( λField1.getText ().toString () ) >= Double.parseDouble ( s.toString () ))
                {
                    isM = true;
                    MField1.setVisibility ( View.VISIBLE );
                }
                else
                    {
                        isM = false;
                        MField1.setVisibility ( View.GONE );
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
    }

    private void initViews()
    {
        λField1 = findViewById ( R.id.λ1 );
        μField1 = findViewById ( R.id.μ1 );
        KField1 = findViewById ( R.id.K1 );
        MField1 = findViewById ( R.id.M1);

        λField1.requestFocus ();

        resultField = findViewById ( R.id.result_field );
    }

    public void resultDD1(View view)
    {
        if(λField1.getText ().toString ().isEmpty () || Double.parseDouble ( λField1.getText ().toString () ) == 0)
        {
            λField1.requestFocus ();
            Toast.makeText ( this, "enter valid λ", Toast.LENGTH_SHORT ).show ();
            return;
        }

        if(μField1.getText ().toString ().isEmpty () || Double.parseDouble ( μField1.getText ().toString () ) == 0)
        {
            μField1.requestFocus ();
            Toast.makeText ( this, "enter valid μ", Toast.LENGTH_SHORT ).show ();
            return;
        }

        if(KField1.getText ().toString ().isEmpty () || Double.parseDouble ( KField1.getText ().toString () ) == 0)
        {
            KField1.requestFocus ();
            Toast.makeText ( this, "enter valid K", Toast.LENGTH_SHORT ).show ();
            return;
        }

        if(isM)
        {
            if(MField1.getText ().toString ().isEmpty () || Double.parseDouble ( MField1.getText ().toString () ) == 0)
            {
                Toast.makeText ( this, "enter valid M", Toast.LENGTH_SHORT ).show ();
                MField1.requestFocus ();
                return;
            }
        }

        double λ = 1/Double.parseDouble ( λField1.getText ().toString () );
        double μ = 1/Double.parseDouble ( μField1.getText ().toString () );
        int K = 1+Integer.parseInt ( KField1.getText ().toString () );

        if(λ < μ || λ == μ)
        {
            int M = Integer.parseInt ( MField1.getText ().toString () );
            int ti = 0;

            if(λ == μ)
            {
                resultField.setText (
                        "ti= " + ti + '\n' +
                                "n(t)= " + M + "\n\n" +

                                "Wq(n)= " + (M-1)*(1/μ) );
            } else
            {
                for(int i = 0 ; i > -1 ; i ++)
                {
                    if(M == Math.floor ( i / (1/μ) ) - Math.floor ( i / (1/λ) ) )
                    {
                        ti = i;
                        break;
                    }
                }

                resultField.setText (
                        "ti= " + ti + '\n' +
                                "n(t)= " + M + "|λt|-|μt|   at t<" + ti + '\n' +
                                "n(t)= 0 or 1   at t=>" + ti + "\n\n" +

                                "Wq(n)= " + Math.floor ( (M-1)/(2*μ) ) + "   at n=0" + '\n' +
                                "Wq(n)= (M-1+n)(1/μ)–n(1/λ)   at n<" + (int)(λ*ti) + '\n' +
                                "Wq(n)= 0   at n=>" + (int)(λ*ti));
            }
            result ();


        }else if(λ > μ)
        {
            int ti = 0;

            for(int i = 0 ; i > -1 ; i ++)
            {
                if(K == Math.floor ( i / (1/λ) ) - Math.floor ( i / (1/μ) - ((1/λ) / (1/μ)) ) )
                {
                    ti = i;
                    break;
                }
            }

            if(λ % μ == 0)
            {
                resultField.setText (
                        "ti= "+ ti + '\n' +
                                "n(t)=0   at t<" + 1/λ + '\n' +
                                "n(t)=|λt|-|μt-μ/λ|   at " + λ + "<t<" + ti + '\n' +
                                "n(t)=" + (K - 1) + "   at t=>" + ti + "\n\n" +
                                "Wq(n)= 0   at n=0" + '\n' +
                                "Wq(n)=" + (1/μ - 1/λ) + "(n-1)   at n<" + (int)(λ * ti) + '\n' +
                                "Wq(n)=" + (int)(1/μ - 1/λ)*(λ*ti-2) +"   at n=>" + (int)(λ * ti));
            }else
            {
                resultField.setText(
                        "ti= "+ ti + '\n' +
                                "n(t)= 0   at t<" + 1/λ + '\n' +
                                "n(t)= |λti|-|μti-μ/λ|   at " + 1/λ + "<t<" + ti + '\n' +
                                "n(t)= " + (K - 1) + " or " + (K - 2) + "   at t=>" + ti + "\n\n" +

                                "Wq(n)= " + (int)(1/μ- 1/λ) + "(n-1)    at n<" + (int)(λ * ti) + '\n' +
                                "Wq(n)= " + (int)(1/μ - 1/λ)*(λ*ti-2) + " or " + (int)(1/μ - 1/λ)*(λ*ti-3) + "   at n=>" + (int)(λ * ti));
            }
            result ();

        }
    }

    private void result()
    {
        λField1.setText ( "" );
        μField1.setText ( "" );
        KField1.setText ( "" );
        MField1.setText ( "" );

        λField1.requestFocus ();
        MField1.setVisibility ( View.GONE );
    }
}