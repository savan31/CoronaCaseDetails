package codes.tuton.coronacasedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONObject;

import codes.tuton.coronacasedetails.method.Data;
import codes.tuton.coronacasedetails.method.GlobalData;
import codes.tuton.coronacasedetails.provider.APIProvider;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView totalCC;
    private TextView todayCC;
    private TextView totalRC;
    private TextView todayRC;
    private TextView totalDC;
    private TextView todayDC;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalCC = findViewById(R.id.totalCC);
        todayCC = findViewById(R.id.todayCC);
        totalRC = findViewById(R.id.totalRC);
        todayRC = findViewById(R.id.todayRC);
        totalDC = findViewById(R.id.totalDC);
        todayDC = findViewById(R.id.todayDC);
        btn = findViewById(R.id.btn);

        final PieChart mPieChart = (PieChart) findViewById(R.id.piechart);
        final String url = "https://api.covid19api.com/summary";

        APIProvider.getInstance(MainActivity.this).sendPOSTRequest(url, null, new APIProvider.ResponseHandler() {
            @Override
            public void getResponse(JSONObject jsonObject) {
                Gson gson =new Gson();
                Data data =gson.fromJson(jsonObject.toString(), Data.class);

                totalCC.setText(data.getGlobal().getTotalConfirmed());
                todayCC.setText(data.getGlobal().getNewConfirmed());
                totalRC.setText(data.getGlobal().getTotalRecovered());
                todayRC.setText(data.getGlobal().getNewRecovered());
                totalDC.setText(data.getGlobal().getTotalDeaths());
                todayDC.setText(data.getGlobal().getNewDeaths());

                mPieChart.addPieSlice(new PieModel("TotalConfirmedCase",Integer.parseInt(data.getGlobal().getTotalConfirmed()), Color.parseColor("#b91400")));
                mPieChart.addPieSlice(new PieModel("TotalRecoverCase",Integer.parseInt(data.getGlobal().getTotalRecovered()), Color.parseColor("#009624")));
                mPieChart.addPieSlice(new PieModel("TotalDecases",Integer.parseInt(data.getGlobal().getTotalDeaths()), Color.parseColor("#4b636e")));
                mPieChart.startAnimation();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AllCountryData.class));
            }
        });
    }
}
