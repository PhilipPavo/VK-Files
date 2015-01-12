package com.pavophilip.android.vk_files.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pavophilip.android.vk_files.R;

/**
 * Created by Philip on 10.01.2015.
 */
public class DocsSaved extends Fragment {
    public static String TAG="SECTION_DOCS_SAVED";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_docs_saved,
                container, false);
        return view;
    }

    public interface OnItemSelectedListener {
        public void onRssItemSelected(String link);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

