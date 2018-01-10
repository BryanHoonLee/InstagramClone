package hoonstudio.com.instagramclone.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import hoonstudio.com.instagramclone.R;

/**
 * Created by joon on 1/7/2018.
 */

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword;
    private TextView mPleaseWait;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mPleaseWait = (TextView) findViewById(R.id.pleaseWait);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = LoginActivity.this;

        Log.d(TAG, "onCreate: started");

        mProgressBar.setVisibility(View.GONE);
        mPleaseWait.setVisibility(View.GONE);

        setupFirebaseAuth();
        init();
    }

    private boolean isStringNull(String string){
        if(string.equals("")){
            return true;
        }else{
            return false;
        }

    }
    private void init(){

        //initializing button for loggin in
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               Log.d(TAG, "onClick: btn_login clicked");

               String email = mEmail.getText().toString();
               String password = mPassword.getText().toString();

               if(isStringNull(email) && isStringNull(password)){
                   Toast.makeText(mContext, "You must fill out all fields", Toast.LENGTH_SHORT).show();
               }else{
                   mProgressBar.setVisibility(View.VISIBLE);
                   mPleaseWait.setVisibility(View.VISIBLE);

                   mAuth.signInWithEmailAndPassword(email, password)
                           .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   Log.d(TAG, "signInWithEmail:onComplete " + task.isSuccessful());

                                   if(!task.isSuccessful()){
                                       Log.w(TAG, "signInWithEmail:failed", task.getException());

                                       Toast.makeText(LoginActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                                   }else{
                                       Log.d(TAG, "signInWithEmail: successful login");
                                       Toast.makeText(LoginActivity.this, R.string.auth_success, Toast.LENGTH_SHORT).show();
                                       mProgressBar.setVisibility(View.GONE);
                                       mPleaseWait.setVisibility(View.GONE);
                                   }
                               }
                           });
               }
           }
        });
    }
      /*
    ------------------------------------------ Firebase --------------------------------------------
     */

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());
                } else{
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();;
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();;
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
