package com.pavophilip.android.vk_files;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pavophilip.android.vk_files.fragments.DocsMy;
import com.pavophilip.android.vk_files.fragments.DocsSaved;
import com.pavophilip.android.vk_files.fragments.SignIn;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;


public class MainActivity extends ActionBarActivity {
    private Toolbar app_bar;
    private VkListener listener = new VkListener(this);
    public User user = new User(this);
    NavigationDrawerFragment drawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VKUIHelper.onCreate(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        app_bar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(app_bar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.init((DrawerLayout) findViewById(R.id.drawer_layout), this, app_bar);
        // VK INITIALIZATION
        VKSdk.initialize(listener, Config.APP_ID, null);
        // VK Authorize User
        if(VKSdk.wakeUpSession() && user.authed()){

        }else{
            selectSection(-1);
        }
    }
    public void onUserInited(){
        app_bar.setVisibility(View.VISIBLE);
        drawerFragment.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        selectSection(0);
    }
    public void selectSection(int i){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(i == -1){
            app_bar.setVisibility(View.INVISIBLE);
            drawerFragment.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }else{
            drawerFragment.menu_list.setItemChecked(i, true);
            app_bar.setTitle(drawerFragment.menu_items.get(i).title);
            drawerFragment.drawerLayout.closeDrawers();
        }
        switch (i){
            case -1:
                SignIn signin = new SignIn();
                SignIn old = (SignIn) manager
                        .findFragmentByTag(signin.TAG);
                if(old == null) transaction.replace(R.id.fragment_container, signin);
                break;
            case 0:
                DocsMy docs_my = new DocsMy();
                transaction.replace(R.id.fragment_container, docs_my);
                DocsMy old_docs_my = (DocsMy) manager
                        .findFragmentByTag(docs_my.TAG);
                if(old_docs_my == null) transaction.replace(R.id.fragment_container, docs_my);
                break;
            case 1:
                DocsSaved docs_saved = new DocsSaved();
                DocsSaved old_docs_saved = (DocsSaved) manager.findFragmentByTag(docs_saved.TAG);
                if(old_docs_saved == null)  transaction.replace(R.id.fragment_container, docs_saved);
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // VK OVERRIDE
    @Override
    protected void onResume() {
        super.onResume();
        VKUIHelper.onResume(this);
        if (user.authed()) {
            user.init();
        } else {
           // user.auth();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VKUIHelper.onActivityResult(this, requestCode, resultCode, data);
    }
}