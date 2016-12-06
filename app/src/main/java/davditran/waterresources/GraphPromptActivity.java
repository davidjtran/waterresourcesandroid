package davditran.waterresources;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import davditran.waterresources.model.PromptData;
import davditran.waterresources.model.SerializationController;

public class GraphPromptActivity extends AppCompatActivity {

    EditText yearText;
    EditText latitudeText;
    EditText longitudeText;
    Spinner graphTypeSpinner;
    Button graphButton;
    Button cancelButton;
    SerializationController serializationController;
    PromptData promptData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_prompt);

        yearText = (EditText) findViewById(R.id.yearText);
        latitudeText = (EditText) findViewById(R.id.latitudeText);
        longitudeText = (EditText) findViewById(R.id.longitudeText);
        graphTypeSpinner = (Spinner) findViewById(R.id.graphTypeSpinner);
        graphButton = (Button) findViewById(R.id.graphButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        //populates graph type spinner
        graphTypeSpinner = (Spinner) findViewById(R.id.graphTypeSpinner);
        ArrayAdapter<CharSequence> graphAdapter = ArrayAdapter.createFromResource(this,
                R.array.graph_type, android.R.layout.simple_spinner_item);
        graphAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        graphTypeSpinner.setAdapter(graphAdapter);

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptData = obtainData();
                Intent graphIntent = new Intent(GraphPromptActivity.this, GraphActivity.class);
                graphIntent.putExtra("PromptData", (Serializable) GraphPromptActivity.this.promptData);
                GraphPromptActivity.this.startActivity(graphIntent);
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public PromptData obtainData() {
        ArrayList<String> data = new ArrayList<>();
        if (isInputValid()) {
            String year = yearText.getText().toString().trim();
            String graphType = graphTypeSpinner.getItemAtPosition(graphTypeSpinner.getSelectedItemPosition()).toString();
            String longitude = longitudeText.getText().toString().trim();
            String latitude = latitudeText.getText().toString().trim();
            promptData = new PromptData(year, graphType, latitude, longitude);
            Log.d("Valid", "Valid data");
            return promptData;
        }
        return null;
    }

    public boolean isInputValid() {
        String errorMessage = "";
        try {
            double year = Double.parseDouble(yearText.getText().toString());
            double latitude = Double.parseDouble(latitudeText.getText().toString());
            double longitude = Double.parseDouble(longitudeText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Latitude and longitude must be a number", Toast.LENGTH_SHORT).show();
            return false;
        }
        //for now just check they actually typed something
        if (yearText.getText().toString() == null || yearText.getText().toString().length() == 0) {
            errorMessage += "No valid year entered!\n";
        }
        if (longitudeText.getText().toString() == null || longitudeText.getText().toString().length() == 0) {
            errorMessage += "No valid longitude entered!\n";
        }
        if (latitudeText.getText().toString() == null || latitudeText.getText().toString().length() == 0) {
            errorMessage += "No valid latitude entered!\n";
        }
//        if (graphTypeSpinner.getItemAtPosition(graphTypeSpinner.getSelectedItemPosition()).toString() == null) {
//            errorMessage += "No valid graph type entered!\n";
//        }
        if (errorMessage.length() == 0) {
            Log.d("VALIDInput", "Valid");
            return true;
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
