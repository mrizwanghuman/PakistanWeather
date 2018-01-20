package com.riz.admin.pakistanweathernew;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingPage extends Fragment {



    public SettingPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_page, container, false);
        ArrayList<String> settingPageItems = new ArrayList<>();
        settingPageItems.add("Zip");
        settingPageItems.add("Search");
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_expandable_list_item_1,
                settingPageItems);
        ListView lvSettingPage = view.findViewById(R.id.lvSettingFrag);
        lvSettingPage.setAdapter(arrayAdapter);
        // Inflate the layout for this fragment
        Log.d("Settingpage", "onCreateView: ");
        return view;
    }

}
