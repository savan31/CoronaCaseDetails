package codes.tuton.coronacasedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codes.tuton.coronacasedetails.adapter.CountryListAdapter;
import codes.tuton.coronacasedetails.method.CountaryData;
import codes.tuton.coronacasedetails.method.Data;
import codes.tuton.coronacasedetails.provider.APIProvider;

public class AllCountryData extends AppCompatActivity {

    private TextView text;
    RecyclerView recyclerViewCountry;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    List<CountaryData> countaryDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_country_data);

        text = findViewById(R.id.text);
        recyclerViewCountry = findViewById(R.id.recyclerViewCountry);

        final String url = "https://api.covid19api.com/summary";


        layoutManager = new LinearLayoutManager(this);
        adapter = new CountryListAdapter(this, countaryDataList);
        recyclerViewCountry.setAdapter(adapter);
        recyclerViewCountry.setLayoutManager(layoutManager);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewCountry.getContext(),DividerItemDecoration.VERTICAL);
        recyclerViewCountry.addItemDecoration(dividerItemDecoration);

        APIProvider.getInstance(AllCountryData.this).sendPOSTRequest(url, null, new APIProvider.ResponseHandler() {
            @Override
            public void getResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                Data data = gson.fromJson(jsonObject.toString(), Data.class);
//                Toast.makeText(AllCountryData.this, countaryDataList.size()+" ", Toast.LENGTH_SHORT).show();
                countaryDataList.addAll(Arrays.asList(data.getCountries()));
                Toast.makeText(AllCountryData.this, countaryDataList.size() + " ", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

    }
}
