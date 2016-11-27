package davditran.waterresources.model;
import java.io.Serializable;


public class Location implements Serializable {

    private final double longitude;
    private final double latitude;
    private String description;
    private String title;

    public Location(double lat, double lg, String ti, String desc) {
        longitude = lg;
        latitude = lat;
        title = ti;
        description = desc;
    }

    public Location() {
        longitude = 0;
        latitude = 0;
        title = "";
        description = "";
    }

    /** to string of latitude and longitude
     * @return String toString of lat and long
     */
    public String toString() {
        return latitude + ", " + longitude;
    }

    /** gets the longitude
     * @return double longitude value
     */
    public double getLongitude() { return longitude; }

    /** gets the latitude
     * @return double latitude value
     */
    public double getLatitude() {return latitude; }

    /** gets the description
     * @return String the description of the location
     */
    public String getDescription() {return description;}

    /** gets the title
     * @return String of the title
     */
    public String getTitle() { return title; }

    /** sets the description
     * @param description the description of the location
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** sets the title
     * @param title the title of the location
     */
    public void setTitle(String title) {
        this.title = title;
    }

}