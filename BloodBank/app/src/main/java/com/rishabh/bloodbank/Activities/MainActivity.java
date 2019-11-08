package com.rishabh.bloodbank.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.rishabh.bloodbank.Adapters.RequestAdapter;
import com.rishabh.bloodbank.DataModels.RequestDataModel;
import com.rishabh.bloodbank.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private List<RequestDataModel> requestDataModels;
  private RequestAdapter requestAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    requestDataModels = new ArrayList<>();
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.search_button) {
          //open search
          startActivity(new Intent(MainActivity.this, SearchActivity.class));
        }
        return false;
      }
    });

    recyclerView = findViewById(R.id.recyclerView);
    LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
    recyclerView.setLayoutManager(layoutManager);
    requestAdapter = new RequestAdapter(requestDataModels, this);
    recyclerView.setAdapter(requestAdapter);
    populateHomePage();
  }


  private void populateHomePage(){
    RequestDataModel requestDataModel = new RequestDataModel("Message:- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. see more...", "https://cdn.pixabay.com/photo/2016/10/17/10/52/wind-farm-1747331__340.jpg");
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestDataModels.add(requestDataModel);
    requestAdapter.notifyDataSetChanged();
  }


}
