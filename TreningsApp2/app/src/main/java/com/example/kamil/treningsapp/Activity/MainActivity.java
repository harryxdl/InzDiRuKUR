package com.example.kamil.treningsapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kamil.treningsapp.DBHelper;
import com.example.kamil.treningsapp.Framgents.CalcNutriValueActivity;
import com.example.kamil.treningsapp.Framgents.HistoryFragment;
import com.example.kamil.treningsapp.Framgents.MeasureFragment;
import com.example.kamil.treningsapp.Framgents.PlanDietManagerFragment;
import com.example.kamil.treningsapp.Models.TreningData;
import com.example.kamil.treningsapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new DBHelper(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Boolean isFirtRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun",true);
        if(isFirtRun)
            switchFragment( new CalcNutriValueActivity(),R.id.nav_calc, "Kalkulator");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_trening) {
            Intent mapsIntent = new Intent(this, MapsActivity.class);
            startActivity(mapsIntent); }
            else if (id == R.id.nav_measure) {
            switchFragment( new MeasureFragment(),R.id.nav_measure, "Measure");
        } else if (id == R.id.nav_history) {
            switchFragment(new HistoryFragment().newInstance(dbHelper.getAllTrenings()), R.id.nav_history, "History");

        } else if (id == R.id.nav_calc) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Bundle bundle = new Bundle();
            bundle.putBoolean("edit", true);
            CalcNutriValueActivity calcNutriValueActivity = new CalcNutriValueActivity();
            calcNutriValueActivity.setArguments(bundle);
            ft.replace(R.id.content_main,calcNutriValueActivity);
            ft.commit();
           //switchFragment( new CalcNutriValueActivity(),R.id.nav_calc, "Kalkulator");
        } else if (id == R.id.nav_measure) {

            switchFragment( new MeasureFragment(),R.id.nav_measure, "Measure");
        }
        else if(id == R.id.nav_diet) {
            switchFragment( new PlanDietManagerFragment(), R.id.nav_diet, "Diet");
        }
        else if (id == R.id. nav_product) {
            Intent intent = new Intent(this, FoodFinderActivity.class);
            startActivity(intent);
        }


        else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void switchFragment(Fragment fragment, int menuID, String TAG){
        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction()
                    .replace(R.id.content_main, fragment, TAG).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void deleteTrening(TreningData treningData) {
        dbHelper.deleeContact(treningData);
    }

    public List<TreningData> getAllTrenings(){
        return dbHelper.getAllTrenings();
    }


}
