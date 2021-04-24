package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   Toolbar toolbar;
   RecyclerView recyclerView;
   Adapter adapter;
   TextView noItemText;
   DiaryDatabase diaryDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
        noItemText = findViewById(R.id.noItemText);
        diaryDatabase = new DiaryDatabase(this);
        setSupportActionBar(toolbar);
        List<Note> allNotes = diaryDatabase.getAllNotes();
        recyclerView = findViewById(R.id.listOfNotes);

        if (allNotes.isEmpty()) {
            noItemText.setVisibility(View.VISIBLE);
        } else {
            noItemText.setVisibility(View.GONE);
            displayList(allNotes);
        }
    }

    private void displayList(List<Note> allNotes) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Adapter(this,allNotes);
            recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add)
        {
            Intent i= new Intent(this,Add_Note.class);
            startActivity(i);
            Toast.makeText(this,"Add Button is clicked.",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        List<Note> getAllNotes = diaryDatabase.getAllNotes();
        if(getAllNotes.isEmpty()){
            noItemText.setVisibility(View.VISIBLE);
        }else {
            noItemText.setVisibility(View.GONE);
            displayList(getAllNotes);
        }


    }
}