package com.vikashkothary.life.ui.stream;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseFragment;

import java.util.ArrayList;

public class StreamFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView =  inflater.inflate(R.layout.fragment_stream, container, false);

        ListView lvItems = (ListView) fragmentView.findViewById(R.id.list_view_todo);
        ArrayList<String> items = new ArrayList<String>();
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        FloatingActionButton fab = (FloatingActionButton) fragmentView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mNewItemEditText = (EditText) fragmentView.findViewById(R.id.edit_text_note);
                String itemText = mNewItemEditText.getText().toString();
                itemsAdapter.add(itemText);
            }
        });



        return fragmentView;
    }

    private void setupListView(){
    }

}
