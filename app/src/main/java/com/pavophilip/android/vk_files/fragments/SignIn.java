package com.pavophilip.android.vk_files.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pavophilip.android.vk_files.MainActivity;
import com.pavophilip.android.vk_files.R;
import com.pavophilip.android.vk_files.User;

/**
 * Created by Philip on 10.01.2015.
 */
public class SignIn extends Fragment {
    public static String TAG="SECTION_SIGNIN";
    View view;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signin,
                container, false);
        btn = (Button) view.findViewById(R.id.auth_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.auth();
            }
        });
        return view;
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
