package davditran.waterresources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import davditran.waterresources.model.Location;
import davditran.waterresources.model.Report;
import davditran.waterresources.model.SerializationController;
import davditran.waterresources.model.User;
import davditran.waterresources.model.WaterQualityReport;

public class SubmitWaterQualityReport extends AppCompatActivity {

    //UI References
    private EditText latitudeText;
    private EditText longitudeText;
    private EditText virusText;
    private EditText containmentText;
    private Button submitButton;
    private Button cancelButton;
    private Spinner typeSpinner;
    private Spinner conditionSpinner;
    private SerializationController serializationController;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_water_quality_report);
        serializationController = SerializationController.getInstance();
        this.user = (User) getIntent().getSerializableExtra("User");

        latitudeText = (EditText) findViewById(R.id.latitudeText);
        longitudeText = (EditText) findViewById(R.id.longitudeText);
        virusText = (EditText) findViewById(R.id.virusText);
        containmentText = (EditText) findViewById(R.id.containmentText);
        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        //creates spinner for water type
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.water_type, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        //creates spinner for water condition
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        ArrayAdapter<CharSequence> conditionAdapter = ArrayAdapter.createFromResource(this,
                R.array.water_condition, android.R.layout.simple_spinner_item);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmitReport();
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onClickSubmitReport() {
        serializationController.retrieveChanges(this, "waterQualityReports");
        ArrayList<WaterQualityReport> reportList = SerializationController.waterQualityReports;
        if (isInputValid()) {
            double longitude = Double.parseDouble(longitudeText.getText().toString());
            double latitude = Double.parseDouble(latitudeText.getText().toString());
            String longString = longitudeText.getText().toString().trim();
            String latString = latitudeText.getText().toString().trim();
            String virusPPM = virusText.getText().toString();
            String containmentPPM = containmentText.getText().toString();
            String type = typeSpinner.getItemAtPosition(typeSpinner.getSelectedItemPosition()).toString();
            String condition = conditionSpinner.getItemAtPosition(conditionSpinner.getSelectedItemPosition()).toString();
            Location loc = new Location(latitude, longitude, type
                    , "Type: " + type + "\nCondition: " + condition + "\nLocation: " + latString + ", " + longString + "\nVirus PPM: " + virusPPM + "\nContainment PPM: " + containmentPPM);
            WaterQualityReport report = new WaterQualityReport(user.getUsername(), loc, type, condition, virusPPM, containmentPPM);
            reportList.add(report);
            serializationController.saveChanges(this, "waterQualityReports", SerializationController.waterQualityReports);
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        try {
            double longitude = Double.parseDouble(longitudeText.getText().toString());
            double latitude = Double.parseDouble(latitudeText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Latitude and longitude must be a number", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            double virusPPM = Double.parseDouble(virusText.getText().toString());
            double containmentPPM = Double.parseDouble(containmentText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Virus and containment must be a number", Toast.LENGTH_SHORT).show();
            return false;
        }
        double longitude = Double.parseDouble(longitudeText.getText().toString());
        double latitude = Double.parseDouble(latitudeText.getText().toString());
        double virusPPM = Double.parseDouble(virusText.getText().toString());
        double containmentPPM = Double.parseDouble(containmentText.getText().toString());
        //for now just check they actually typed something
        if (longitudeText.getText().toString() == null || longitudeText.getText().toString().length() == 0) {
            errorMessage += "No valid location entered!\n";
        }
        if (latitudeText.getText() == null || latitudeText.getText().length() == 0) {
            errorMessage += "No valid location entered!\n";
        }
        if (virusText.getText() == null || virusText.getText().length() == 0) {
            errorMessage += "No valid Virus PPM entered!\n";
        }
        if (containmentText.getText() == null || containmentText.getText().length() == 0) {
            errorMessage += "No valid Containment PPM entered!\n";
        }
        if (longitude > 180 || longitude < -180) {
            errorMessage += "Longitude field is invalid!\n";
        }
        if (latitude > 90 || latitude < -90) {
            errorMessage += "Latitude field is invalid!\n";
        }
        if (virusPPM < 0) {
            errorMessage += "VirusPPM cannot be negative";
        }
        if (containmentPPM < 0) {
            errorMessage += "VirusPPM cannot be negative";
        }
        if (conditionSpinner.getItemAtPosition(conditionSpinner.getSelectedItemPosition()).toString() == null) {
            errorMessage += "No valid water condition entered!\n";
        }
        if (typeSpinner.getItemAtPosition(typeSpinner.getSelectedItemPosition()).toString() == null) {
            errorMessage += "No valid water type entered!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
