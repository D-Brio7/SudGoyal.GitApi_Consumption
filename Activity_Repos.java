package sudgoyal.gitusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Repos extends AppCompatActivity {
    RecyclerView repoList;
    ArrayList<repoData> RepoList;

    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__repos);

        Bundle extras = getIntent().getExtras();
        String user = extras.getString("USER");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        //TODO:Initialise

        repoList = findViewById(R.id.repoList);

        RepoList = new ArrayList<>();

        url = "https://api.github.com/users/" +user+ "/repos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i  = 0; i < response.length(); i++)
                {
                    JSONObject data;
                    try {
                        data = response.getJSONObject(i);
                        Log.d("DATA", "onResponse: " + data.getString("name"));
                        RepoList.add(new repoData(data.getString("name"),data.getString("url")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                repoList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                ReposAdapter repo = new ReposAdapter(RepoList,Activity_Repos.this);
                repoList.setAdapter(repo);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }
}
