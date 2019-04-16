package com.thedeveloper.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText et1=(EditText)findViewById(R.id.et1);
        final EditText et2=(EditText)findViewById(R.id.et2);
        Button btn=(Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=et1.getText().toString().toLowerCase().trim();
                String password=et2.getText().toString().toLowerCase().trim();
                if(username.length()==0 && password.length()==0)
                {
                    Toast.makeText(Login.this,"Enter UserName and Password",Toast.LENGTH_LONG).show();
                    et1.requestFocus();
                }
                else  if(username.length()==0)
                {
                    Toast.makeText(Login.this,"Enter UserName",Toast.LENGTH_LONG).show();
                    et1.requestFocus();
                }
                else  if(password.length()==0)
                {
                    Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_LONG).show();
                    et2.requestFocus();
                }
                else
                {
                    DatabaseHandler db = new DatabaseHandler(Login.this);

                    Users userdetails = db.getUsers2(username);

                    if(userdetails==null)
                    {
                        Toast.makeText(Login.this,"User Does Not Exist",Toast.LENGTH_LONG).show();
                        et1.requestFocus();
                    }
                    else
                    {
                        String pass =userdetails.getPassword();
                        String encryptedString = AES.decrypt(pass,"123456");
                        if(encryptedString.equals(password))
                        {
                            Toast.makeText(Login.this,"Login Successfull",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Wrong Password",Toast.LENGTH_LONG).show();
                        }

                    }

                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in2,R.anim.slide_out2);
    }
}
