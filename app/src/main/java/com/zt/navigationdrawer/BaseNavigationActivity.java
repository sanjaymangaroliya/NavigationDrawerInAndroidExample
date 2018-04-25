package com.zt.navigationdrawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseNavigationActivity extends AppCompatActivity {

    protected static final int NAV_DRAWER_ITEM_INVALID = -1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private View view;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        getSetActionBarToolbar();
        setupNavDrawer();
    }

    private void setupNavDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            return;
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                changeInfo(view);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerSelectListener(navigationView);
            setSelectedItem(navigationView);
            view = navigationView.getHeaderView(0);
            changeInfo(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (BaseNavigationActivity.this instanceof MainActivity) {
                        closeDrawer();
                        return;
                    }
                    goToNavDrawerItem(R.id.nav_android);
                }
            });
        }
    }

    private void setSelectedItem(NavigationView navigationView) {
        int selectedItem = getSelfNavDrawerItem();
        navigationView.setCheckedItem(selectedItem);
    }

    private void setupDrawerSelectListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        drawerLayout.closeDrawers();
                        onNavigationItemClicked(menuItem.getItemId());
                        return true;
                    }

                });
    }

    private void onNavigationItemClicked(final int itemId) {
        if (itemId == getSelfNavDrawerItem()) {
            closeDrawer();
            return;
        }
        goToNavDrawerItem(itemId);
    }

    private void goToNavDrawerItem(int item) {
        switch (item) {
            case R.id.nav_android:
                Intent intent_android = new Intent(this, MainActivity.class);
                intent_android.putExtra("title", getString(R.string.android));
                startActivity(intent_android);
                this.finish();
                break;
            case R.id.nav_ios:
                Intent intent_ios = new Intent(this, MainActivity.class);
                intent_ios.putExtra("title", getString(R.string.ios));
                startActivity(intent_ios);
                this.finish();
                break;
            case R.id.nav_java:
                Intent intent_java = new Intent(this, MainActivity.class);
                intent_java.putExtra("title", getString(R.string.java));
                startActivity(intent_java);
                this.finish();
                break;
            case R.id.nav_php:
                Intent intent_php = new Intent(this, MainActivity.class);
                intent_php.putExtra("title", getString(R.string.php));
                startActivity(intent_php);
                this.finish();
                break;
            case R.id.nav_asp_net:
                Intent intent_asp_net = new Intent(this, MainActivity.class);
                intent_asp_net.putExtra("title", getString(R.string.asp_net));
                startActivity(intent_asp_net);
                this.finish();
                break;
        }
    }

    protected ActionBar getSetActionBarToolbar() {
        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }

        }
        return getSupportActionBar();
    }

    protected int getSelfNavDrawerItem() {
        return NAV_DRAWER_ITEM_INVALID;
    }

    protected void closeDrawer() {
        if (drawerLayout == null)
            return;
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(Gravity.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeInfo(View view) {
        ImageView img_profile = view.findViewById(R.id.img_profile);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_email = view.findViewById(R.id.tv_email);
        //tv_name.setText("");
        //tv_email.setText("");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Sure, You want exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
}
