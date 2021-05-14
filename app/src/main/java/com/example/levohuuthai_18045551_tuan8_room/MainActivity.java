package com.example.levohuuthai_18045551_tuan8_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtPut;
    Button btnAdd;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    UserDao userDao;
    UserAdapter userAdapter;
    List<User> mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPut = findViewById(R.id.edtPut);
        btnAdd = findViewById(R.id.btnAdd);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycle);



        userAdapter = new UserAdapter();
        mlist = new ArrayList<>();
        userAdapter.setData(mlist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(userAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFUserName = edtPut.getText().toString().trim();
                if(TextUtils.isEmpty(strFUserName)) {
                    return;
                }
                User user = new User(strFUserName);
                AppDatabase.getInstance(MainActivity.this).userDao().insertAll(user);
                Toast.makeText(MainActivity.this,"thanhcong",Toast.LENGTH_SHORT).show();
                edtPut.setText("");

                mlist = AppDatabase.getInstance(MainActivity.this).userDao().getAll();
                userAdapter.setData(mlist);
            }
        });

    }
}