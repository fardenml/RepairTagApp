package com.example.flyer.testdatabase;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.*;



public class MainActivity extends AppCompatActivity {
    public EditText first;
    public EditText last;
    public EditText number;
    public Button submit;
    public TextView error;
    /*public void dbConnect(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.11:3306/Midstate", "music", "clarkson2018");
        //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customers");
            while (rs.next()){
                first.setText(rs.getString("first"));
                last.setText(rs.getString("last"));
                number.setText(rs.getString("number"));
            }

            con.close();
        } catch (Exception e) {
            first.setText(e.toString());
        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first = (EditText) findViewById(R.id.editText);
        last = (EditText) findViewById(R.id.editText2);
        number = (EditText) findViewById(R.id.editText3);
        submit = (Button) findViewById(R.id.submit);
        error = (TextView) findViewById(R.id.error);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://192.168.1.11:3306/Midstate", "music", "clarkson2018");
                    Statement stmt = con.createStatement();

                    String command = "insert into customers values (\"" + first.getText() + "\", \"" + last.getText() + "\", \"" + number.getText() + "\")";


                    stmt.executeUpdate(command);

                    error.setText("DONE");


                    con.close();
                } catch (Exception e) {
                    error.setText(e.toString());
                }
            }
        });
    }
}


