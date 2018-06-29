package com.example.doffy.negocioselectronicos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class NumberVerification extends AppCompatActivity {

    /*DECLARACION DE VARIABLES*/
    //Layouts
    private LinearLayout llNumberVerification;
    private LinearLayout llCodeVerification;
    //controles de la interfaz
    private ImageButton btnEnviar;
    private EditText txtPhone;
    private EditText txtCodRegion;
    private EditText txtCode;
    //private Button btnReenviar;
    //Firebase Auth
    private FirebaseAuth fbAuth;
    //private String cPhoneNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verification);

        /*Inicializacion de variables*/
        //Layouts
        llNumberVerification = (LinearLayout) findViewById(R.id.llNumberVerification);
        llCodeVerification = (LinearLayout) findViewById(R.id.llCodeVerification);
        //controles de la interfaz
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        btnEnviar = (ImageButton) findViewById(R.id.btnEnviar);
        txtCodRegion = (EditText) findViewById(R.id.txtCodRegion);
        txtCode = (EditText) findViewById(R.id.txtCode);
        //btnReenviar = (Button) findViewById(R.id.btnReenviar);


        //Firebase AUTH
        fbAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                txtCode.setText(phoneAuthCredential.getSmsCode());
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(NumberVerification.this,"Sign failed¡",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                Toast.makeText(NumberVerification.this,"Codigo Verificado",Toast.LENGTH_SHORT).show();
            }
        };
        //Evento click de btnEnviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cPhoneNumber=txtCodRegion.getText().toString()+txtPhone.getText().toString();
                Toast.makeText(NumberVerification.this,""+cPhoneNumber,Toast.LENGTH_SHORT).show();
                llNumberVerification.setVisibility(View.GONE);
                llCodeVerification.setVisibility(View.VISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        cPhoneNumber,
                        60,
                        TimeUnit.SECONDS,
                        NumberVerification.this,
                        mCallbacks
                );
                Toast.makeText(NumberVerification.this,"Se ha enviado la solicitud",Toast.LENGTH_SHORT).show();
            }
        });
        /*btnReenviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*Toast.makeText(NumberVerification.this,""+cPhoneNumber,Toast.LENGTH_SHORT).show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        cPhoneNumber,
                        60,
                        TimeUnit.SECONDS,
                        NumberVerification.this,
                        mCallbacks
                );
                Toast.makeText(NumberVerification.this,"Se ha enviado la solicitud",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            Intent iMainActivity=new Intent(NumberVerification.this, MainActivity.class);
                            startActivity(iMainActivity);
                            finish();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(NumberVerification.this,"Sign failed¡",Toast.LENGTH_SHORT).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(NumberVerification.this,"Code Invalid¡",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }

}
