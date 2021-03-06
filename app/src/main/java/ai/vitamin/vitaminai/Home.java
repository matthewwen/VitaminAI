package ai.vitamin.vitaminai;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ai.vitamin.vitaminai.R;
import ai.vitamin.vitaminai.data.DataContract;
import ai.vitamin.vitaminai.data.DataMethod;
import ai.vitamin.vitaminai.fragments.AboutFragment;
import ai.vitamin.vitaminai.fragments.HomeFragment;
//import ai.vitamin.vitaminai.fragments.MealsFragment;
import ai.vitamin.vitaminai.fragments.MealsFragment;
import ai.vitamin.vitaminai.fragments.TrendFragment;
import ai.vitamin.vitaminai.introscreen.Intro;
import ai.vitamin.vitaminai.objects.Food;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (!PreferenceUtils.didStartUp(this)){
            Log.v(Home.class.getSimpleName(), "Intro Screen should show");
            Intent intent = new Intent(this, Intro.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            Log.v(Home.class.getSimpleName(), "Nah Bitch");
        }

        //getting the views
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        //setting the behavior declared above to the bottom navigation object
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //displaying the first view -> navigation home
        loadFragment(HomeFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = HomeFragment.newInstance();
                break;
            case R.id.navigation_trends:
                fragment = TrendFragment.newInstance();
                break;
            case R.id.navigation_meal_logs:
                fragment = MealsFragment.newInstance();
                break;
            case R.id.navigation_about:
                fragment = AboutFragment.newInstance();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_home_frame_layout, fragment)
                    .commit();
            return true;
        }

        return false;
    }
}
