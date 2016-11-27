package davditran.waterresources.model;

/**
 * Created by David on 11/26/2016.
 */

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Report implements Serializable {

    private String dateTime = "";
    private String reportNumber = "";
    private String reporterName = "";
    private Location location = new Location();
    private String waterType = "";
    private String waterCondition = "";
    private SerializationController serializationController;

    private static final long serialVersionUID = 1L;


    public Report() {
        DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy h:mm a");
        String date = dateFormat.format(new Date());
        setDateTime(date);
    }

    public Report(String reporterName, Location location, String waterType, String waterCondition) {
        serializationController = SerializationController.getInstance();
        ArrayList<Report> reports = serializationController.reports;
        setReportNumber(String.format("%05d", reports.size() + 1));
        setReporterName(reporterName);
        setLocation(location);
        setWaterType(waterType);
        setWaterCondition(waterCondition);
        DateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy h:mm a");
        String date = dateFormat.format(new Date());
        setDateTime(date);
    }

    /**
     * gets the year of the report
     * @return String of the year
     */
    public String getYear() {
        return getDateTime().substring(7, 11);
    }

    private String getDateTime() {
        return dateTime;
    }

    public String dateTimeProperty() {
        return dateTime;
    }

    private void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    private String getReportNumber() {
        return reportNumber;
    }

    private void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    private String getReporterName() {
        return reporterName;
    }

    private void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public Location getLocation() {
        return location;
    }

    private void setLocation(Location location) {
        this.location = location;
    }

    private String getWaterType() {
        return waterType;
    }

    private void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    private String getWaterCondition() {
        return waterCondition;
    }

    private void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }

    public String toString() {
        return "Report Number: " + getReportNumber() + "\nReporter Name: " + getReporterName() + "\nDate Reported: " + getDateTime() + "\nLocation: " + getLocation().toString() + "\nWater Type: " + getWaterType() + "\nWater Condition: " + getWaterCondition();
    }

}