package com.saha.arun.visualtraceroute.Activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saha.arun.visualtraceroute.Adapters.HistoryAdapter;
import com.saha.arun.visualtraceroute.R;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import github.nisrulz.recyclerviewhelper.RVHItemClickListener;
import github.nisrulz.recyclerviewhelper.RVHItemDividerDecoration;
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private String dest_ip;
    private RecyclerView recyclerView;
    private TextView empty_list_tv;
    private HistoryAdapter adapter;
    private ArrayList<String> data;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        empty_list_tv = (TextView) findViewById(R.id.empty_list);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();


        data = new ArrayList<>();

        if (data.size() == 0) {
            recyclerView.setVisibility(View.GONE);
        } else {
            empty_list_tv.setVisibility(View.GONE);
        }

        adapter = new HistoryAdapter(data,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup onItemTouchHandler
        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(adapter, true, true, true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        //divider
        recyclerView.addItemDecoration(
                new RVHItemDividerDecoration(this, LinearLayoutManager.VERTICAL));


        // Set On Click
        recyclerView.addOnItemTouchListener(
                new RVHItemClickListener(this, new RVHItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        input.setText(data.get(position));
                        try {
                            dest_ip = new NetTask().execute(input.getText().toString()).get();
                            if (dest_ip.contains("Error")) {
                                Toast.makeText(getApplicationContext(), dest_ip, Toast.LENGTH_LONG).show();
                            } else {
                                Log.e("asd", dest_ip);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }));

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    try {
                        dest_ip = new NetTask().execute(input.getText().toString()).get();
                        if (dest_ip.contains("Error")) {
                            Toast.makeText(getApplicationContext(), dest_ip, Toast.LENGTH_LONG).show();
                        } else {
                            Log.e("asd", dest_ip);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
    }
}


class NetTask extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        InetAddress addr;
        try {
            addr = InetAddress.getByName(params[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
        return addr.getHostAddress();
    }
}