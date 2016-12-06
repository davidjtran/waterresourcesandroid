package davditran.waterresources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import davditran.waterresources.model.Report;
import davditran.waterresources.model.SerializationController;

public class ViewReportActivity extends AppCompatActivity {

    //UI references
    ListView listView;
    Button backButton;
    SerializationController serializationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        listView = (ListView) findViewById(R.id.listView);
        backButton = (Button) findViewById(R.id.backButton);

        serializationController = SerializationController.getInstance();
        serializationController.retrieveChanges(this, "reports");
        List<Report> reportList = serializationController.reports;
        List<String> reportsAsStrings = new ArrayList<>();
        for (Report r : reportList) {
            reportsAsStrings.add(r.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                reportsAsStrings);
        listView.setAdapter(arrayAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
