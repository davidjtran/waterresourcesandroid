package davditran.waterresources;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import davditran.waterresources.model.PromptData;
import davditran.waterresources.model.SerializationController;
import davditran.waterresources.model.WaterQualityReport;

public class GraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    GraphView graph;
    PromptData promptData;
    String year;
    String graphType;
    String latitude;
    String longitude;
    ArrayList<WaterQualityReport> reports;
    SerializationController serializationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        serializationController = SerializationController.getInstance();
        serializationController.retrieveChanges(this, "waterQualityReports");
        reports = serializationController.waterQualityReports;

        this.promptData = (PromptData) getIntent().getSerializableExtra("PromptData");

        if (promptData == null) {
            Toast.makeText(this, "Application did not have sufficient data. Graph terminated.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            year = promptData.getYear();
            graphType = promptData.getGraphType();
            latitude = promptData.getLatitude();
            longitude = promptData.getLongitude();
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<>();
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(13);
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setXAxisBoundsManual(true);
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(32);
        glr.setHorizontalAxisTitle("Months");
        glr.setVerticalAxisTitle("PPM Level");

        if (graphType.equals("Virus PPM")) {
            HashMap<Integer, Double> virusData = getVirusData();
            for (Integer month : virusData.keySet()) {
                Integer x = month;
                Double y = virusData.get(month);
                series.appendData(new DataPoint(x, y), true, 50);
            }
        }

        if (graphType.equals("Containment PPM")) {
            HashMap<Integer, Double> containmentData = getContainmentData();
            for (Integer month : containmentData.keySet()) {
                Integer x = month;
                Double y = containmentData.get(month);
                series.appendData(new DataPoint(x, y), true, 50);
            }
        }

        graph.addSeries(series);
    }

    public ArrayList<WaterQualityReport> getWaterQualityData() {
        ArrayList<WaterQualityReport> data = new ArrayList<>();
        double latDouble = Double.parseDouble(latitude);
        double longDouble = Double.parseDouble(longitude);
        for (WaterQualityReport r : reports) {
            if (r.getYear().equals(year) &&
                    r.getLocation().getLatitude() == latDouble && r.getLocation().getLongitude() == longDouble) {
                data.add(r);
            }
        }
        return data;
    }

    public HashMap<String, Integer> decodeMonth(){
        HashMap<String, Integer> month = new HashMap<>();
        month.put("Jan", 1);
        month.put("Feb", 2);
        month.put("Mar", 3);
        month.put("Apr", 4);
        month.put("May", 5);
        month.put("Jun", 6);
        month.put("Jul", 7);
        month.put("Aug", 8);
        month.put("Sep", 9);
        month.put("Oct", 10);
        month.put("Nov", 11);
        month.put("Dec", 12);
        return month;
    }

    public HashMap<Integer, Double> getVirusData() {
        HashMap<Integer, Double> virusData = new HashMap<>();
        ArrayList<WaterQualityReport> reportData = getWaterQualityData();
        HashMap<String, Integer> decode = decodeMonth();
        for (WaterQualityReport r : reportData) {
            int month = decode.get(r.getMonth());
            Double virusPPM = Double.parseDouble(r.getVirusPPM());
            if (virusData.get(month) == null) {
                virusData.put(month, virusPPM);
            } else {
                Double oldPPM = virusData.get(month);
                Double average = (oldPPM + virusPPM) / 2;
                virusData.put(month, average);
            }
        }
        return virusData;
    }

    public HashMap<Integer, Double> getContainmentData() {
        HashMap<Integer, Double> containmentData = new HashMap<>();
        ArrayList<WaterQualityReport> reportData = getWaterQualityData();
        HashMap<String, Integer> decode = decodeMonth();
        for (WaterQualityReport r : reportData) {
            int month = decode.get(r.getMonth());
            Double containmentPPM = Double.parseDouble(r.getContamPPM());
            if (containmentData.get(month) == null) {
                containmentData.put(month, containmentPPM);
            } else {
                Double oldPPM = containmentData.get(month);
                Double average = (oldPPM + containmentPPM) / 2;
                containmentData.put(month, average);
            }
        }
        return containmentData;
    }
}
