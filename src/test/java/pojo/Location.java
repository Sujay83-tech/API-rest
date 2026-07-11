package pojo;

public class Location {

    // Google Places API nests location as an OBJECT { lat, lng },
    // NOT a flat string like "-38.38,33.42".
    // This is why a separate Location class exists — it mirrors
    // the nested JSON shape exactly, one Java class per JSON object level.
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}