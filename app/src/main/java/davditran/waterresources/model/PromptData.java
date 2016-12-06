package davditran.waterresources.model;

import android.content.ServiceConnection;

import java.io.Serializable;

/**
 * Created by David on 12/6/2016.
 */

public class PromptData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String year;
    private String graphType;
    private String longitude;
    private String latitude;

    public PromptData(String year, String graphType, String latitude, String longitude) {
        this.year = year;
        this.graphType = graphType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getYear(){
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGraphType(){
        return this.graphType;
    }
    public void setGraphType(String graphType) {
        this.year = graphType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.longitude = latitude;
    }

}
