package com.example.usgir.navigationdrawer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,ChatRoom {
    DrawerLayout drawerLayout;
    ListView listView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    ActionBarDrawerToggle drawerToggle;
    String s[];
    HomeFragment homeFragment;
    AccountFragment accountFragment;
    LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer1);
        listView = (ListView)findViewById(R.id.list);
        s = getResources().getStringArray(R.array.items);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.itemlist,R.id.textView4,s);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                //Toast.makeText(getApplicationContext(),"yup",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //Toast.makeText(getApplicationContext(),"yup",Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        loginFragment = new LoginFragment();
        fragmentManager = getFragmentManager();
        Homeclick();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitems,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"You selected : "+s[position],Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(s[position]);
        switch (position)
        {
            case 0 : Homeclick();
                     break;
            case 1 : Accountclick();
                     break;
            case 2 : Loginclick();
                     break;
            default: Homeclick();
        }
        drawerLayout.closeDrawer(listView);
        //listView.setSelection(position);
        //view.setBackgroundColor(getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void Homeclick() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,homeFragment,"Home");
        transaction.commit();
    }

    @Override
    public void Accountclick() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,accountFragment,"Account");
        transaction.commit();
    }

    @Override
    public void Loginclick() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,loginFragment,"Account");
        transaction.commit();
    }
}
