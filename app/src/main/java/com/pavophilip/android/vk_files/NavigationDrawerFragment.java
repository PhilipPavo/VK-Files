package com.pavophilip.android.vk_files;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pavophilip.android.vk_files.fragments.DocsMy;
import com.pavophilip.android.vk_files.fragments.DocsSaved;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawerFragment extends Fragment {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar app_bar;
    MenuAdapter menuAdapter;
    MainActivity main;
    //private RecyclerView recyclerView;
    public ListView menu_list;
    List<MenuItem> menu_items = new ArrayList<>();
    public NavigationDrawerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        menu_list = (ListView) layout.findViewById(R.id.drawerList);
        MenuAdapter adapter = new MenuAdapter(layout.getContext(), menu_items);
        menu_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        menu_list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.selectSection(position);
            }
        });
        menu_list.setAdapter(adapter);
        //recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        //menuAdapter = new MenuAdapter(getActivity(), this.getItems());
        //recyclerView.setAdapter(menuAdapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(layout.getContext(), DividerItemDecoration.VERTICAL_LIST));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }
    public void init(DrawerLayout drawerLayout, MainActivity main, Toolbar app_bar) {
        this.main = main;
        this.app_bar = app_bar;
        this.drawerLayout = drawerLayout;
        this.drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, app_bar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        int[] icons = {R.drawable.ic_menu_docs_my, R.drawable.ic_menu_docs_saved};
        String[] titles = {"Мои документы", "Сохраненные"};
        for(int i =0; i < icons.length && i < icons.length; i++){
            MenuItem item = new MenuItem();
            item.iconId = icons[i];
            item.title = titles[i];
            menu_items.add(item);
        }

        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }
    public void setSectionTitle(String title){
        app_bar.setTitle(title);
    }
}
