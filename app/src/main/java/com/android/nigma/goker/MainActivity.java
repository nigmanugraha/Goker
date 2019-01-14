package com.android.nigma.goker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.nigma.goker.Fragment.AccountFragment;
import com.android.nigma.goker.Fragment.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    HomeFragment homeFragment;
    AccountFragment accountFragment;
    FirebaseAuth auth;
    FirebaseUser user;

    private BottomNavigationView btmMenu;
    private int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        btmMenu = (BottomNavigationView) findViewById(R.id.navigation);
        btmMenu.setOnNavigationItemSelectedListener(this);

        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();

        loadFragment(homeFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home: {
                fragment = homeFragment;
                break;
            }
            case R.id.navigation_account: {
                if(user == null){
                    Toast.makeText(this, "Testing", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                    return false;
                }
                fragment = accountFragment;
                break;
            }
        }
        loadFragment(fragment);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = auth.getCurrentUser();
        if(accountFragment.isVisible() && user == null){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction ft = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        ft.replace(R.id.frameLayout, fragment);
        ft.commit(); // save the changes
    }

}
