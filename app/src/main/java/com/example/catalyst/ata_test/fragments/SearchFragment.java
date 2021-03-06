package com.example.catalyst.ata_test.fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.catalyst.ata_test.R;
import com.example.catalyst.ata_test.adapters.DashboardAdapter;
import com.example.catalyst.ata_test.adapters.SearchResultAdapter;
import com.example.catalyst.ata_test.data.DBHelper;
import com.example.catalyst.ata_test.models.Team;
import com.example.catalyst.ata_test.models.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dsloane on 4/27/2016.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getSimpleName();

    private SearchResultAdapter adapter;

    @Bind(android.R.id.list)ListView listView;
    private View resultView;
    private ArrayList<User> results = new ArrayList<User>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultView = inflater.inflate(R.layout.content_search, null);
        ButterKnife.bind(this, resultView);

        adapter = new SearchResultAdapter(getActivity(), results);

        listView.setAdapter(adapter);

        Intent intent = getActivity().getIntent();
        String query = intent.getStringExtra(SearchManager.QUERY);

        searchUsers(query);

        Log.d(TAG, "in the result fragment!!!!!!!!!");

        return resultView;
    }

    public void searchUsers(String query) {
        results.clear();
        DBHelper dbHelper = new DBHelper(getActivity());

        ArrayList<User> users = dbHelper.searchUsers(query);
        for (User user : users) {
            Log.d(TAG, "getting " + user.getFirstName());
            results.add(user);
        }
        dbHelper.close();
        adapter.notifyDataSetChanged();
    }
}
