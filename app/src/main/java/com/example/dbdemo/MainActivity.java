package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText user, password;
    Button add, view, home;
    Switch active;
    ListView listView;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        add=findViewById(R.id.add);
        view=findViewById(R.id.view);
        active=findViewById(R.id.active);
        home=findViewById(R.id.home);
        listView=findViewById(R.id.listview);

        dataBaseHelper=new DataBaseHelper(MainActivity.this);
        showLoginOnListView(dataBaseHelper);

        //button listeners for the add and view
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LoginModel loginModel;

                try{
                    loginModel=new LoginModel(-1, user.getText().toString(), Integer.parseInt(password.getText().toString()), active.isChecked());
                    Toast.makeText(MainActivity.this, loginModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this,"error creating login", Toast.LENGTH_SHORT).show();
                    loginModel=new LoginModel(-1, "error", 0, false);
                }
                dataBaseHelper=new DataBaseHelper(MainActivity.this);
                boolean success=dataBaseHelper.addOne(loginModel);
                Toast.makeText(MainActivity.this, "Success="+success, Toast.LENGTH_SHORT).show();
                showLoginOnListView(dataBaseHelper);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper=new DataBaseHelper(MainActivity.this);
                showLoginOnListView(dataBaseHelper);

                // Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoginModel clickUser=(LoginModel)adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickUser);
                showLoginOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted "+clickUser.toString(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void showLoginOnListView(DataBaseHelper dataBaseHelper2) {


        ArrayAdapter loginArrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        listView.setAdapter(loginArrayAdapter);
    }
}
