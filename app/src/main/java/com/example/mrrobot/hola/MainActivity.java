package com.example.mrrobot.hola;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrrobot.hola.Services.ServiceLocation;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
        OnMenuItemClickListener, OnMenuItemLongClickListener

        {



    // menu
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        //ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);

        initUI();
        initToolbar();
        initMenuFragment();
        addFragment(new MainFragment(), true, R.id.container);




        // set listener for menu


    }
    private void initUI(){


    }
    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize(250);
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);

    }
    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.menu:
                mMenuDialogFragment.show(fragmentManager, "ContextMenuDialogFragment");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        // el position=cero es el "x"

        switch (position){
            case 1:
                // search
                showSearchDialog();
                break;
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        //Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }
    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setBgResource(R.color.colorPrimaryDark);
        close.setResource(R.drawable.ic_close_white_24dp);
        close.setDividerColor(R.color.colorPrimaryDark);
        //close.setMenuTextAppearanceStyle(R.style.TextViewStyle);

        MenuObject search = new MenuObject();
        search.setBgResource(R.color.colorPrimaryDark);
        search.setResource(R.drawable.ic_search_white_24dp);
        search.setDividerColor(R.color.colorPrimaryDark);
        //search.setMenuTextAppearanceStyle(R.style.TextViewStyle);

        MenuObject chat = new MenuObject();
        chat.setBgResource(R.color.colorPrimaryDark);
        chat.setResource(R.drawable.ic_chat_white_24dp);
        chat.setDividerColor(R.color.colorPrimaryDark);
        //chat.setMenuTextAppearanceStyle(R.style.TextViewStyle);

        MenuObject group = new MenuObject();
        group.setBgResource(R.color.colorPrimaryDark);
        group.setResource(R.drawable.ic_group_add_white_24dp);
        group.setDividerColor(R.color.colorPrimaryDark);
        //group.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        MenuObject user = new MenuObject();
        user.setBgResource(R.color.colorPrimaryDark);
        user.setResource(R.drawable.ic_person_white_24dp);
        //user.setMenuTextAppearanceStyle(R.style.TextViewStyle);
        user.setDividerColor(R.color.colorPrimaryDark);


        menuObjects.add(close);
        menuObjects.add(search);
        menuObjects.add(chat);
        menuObjects.add(group);
        menuObjects.add(user);
        return menuObjects;
    }
    // END MENU



    @Override
    public void onClick(View view) {

    }

    private void showSearchDialog(){
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder( new ViewHolder(R.layout.dialog_search))
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                    }
                })
                //.setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300

                .setGravity(Gravity.TOP)
                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        dialog.show();
    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    */
}