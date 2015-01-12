package com.pavophilip.android.vk_files.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pavophilip.android.vk_files.CircularImageView;
import com.pavophilip.android.vk_files.CustomDialog;
import com.pavophilip.android.vk_files.FileItem;
import com.pavophilip.android.vk_files.FilesListAdapter;
import com.pavophilip.android.vk_files.MenuItem;
import com.pavophilip.android.vk_files.R;
import com.pavophilip.android.vk_files.Utils;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 10.01.2015.
 */
public class DocsMy extends Fragment implements AdapterView.OnItemClickListener {
    public static String TAG="SECTION_DOCS_MY";
    List<FileItem> files = new ArrayList<>();
    ListView files_list;
    Context context;
    LayoutInflater inflater;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(savedInstanceState == null) this.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_docs_my,
                container, false);
        this.inflater = inflater;
        this.context = view.getContext();
        this.files_list = (ListView) view.findViewById(R.id.files_list);
        files_list.setOnItemClickListener(this);
        return view;
    }

    public void init(){
        System.out.println("STARTUING_----------------------------");
        VKRequest request = new VKRequest("docs.get");
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                System.out.println("DONE LOADING ----------------------------");
                try {
                    JSONArray items = (JSONArray) ((JSONObject) ((JSONObject)response.json).get("response")).getJSONArray("items");
                    for(int i = 0; i < items.length(); i++) {
                        FileItem file = new FileItem();
                        JSONObject item = (JSONObject) items.get(i);
                        file.title = item.getString("title");
                        file.ext = item.getString("ext");
                        file.size = item.getInt("size");
                        file.url = item.getString("url");
                        //if(item.isNull("photo_100")) file.photo_100 = null;
                        //else file.photo_100 = item.getString("photo_100");
                        files.add(file);
                    }
                    FilesListAdapter adapter = new FilesListAdapter(context, files);
                    files_list.setAdapter(adapter);
                    System.out.println("DONE ALL SET ADDAPTER----------------------------");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VKError error) {
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {

            }
        });
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CustomDialog dialog = new CustomDialog(context)
                .setCustomTitle("Документ")
                .setText(files.get(position).title)
                .setCancelButton("Отмена")
                .setCustomContent(inflater.inflate(R.layout.dialog_file_my, null));
        dialog.show();
        //dialog.setTitle("Title...");
    }
}
