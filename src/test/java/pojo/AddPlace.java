package pojo;

import java.util.List;

public class AddPlace {

    // int, not double — matches the real API's expected type for accuracy.
    // Sending a double where the API expects int can silently serialize
    // wrong (e.g. "50.0" instead of "50") and cause a 400 Bad Request.

    private int accuracy;

    private String name;
    private String phone_number;   // snake_case here because the JSON key itself is snake_case;
    // Jackson maps Java field name -> JSON key by default,
    // so the field name must match the API's key exactly
    // unless you use @JsonProperty to remap it.
    private String address;
    private String website;
    private String language;

    // Nested object -> nested POJO. This field being type "Location"
    // (not String) is what makes Jackson serialize it as:
    //   "location": { "lat": ..., "lng": ... }
    // instead of a flat string.
    private Location location;

    // A JSON array of strings -> List<String> in Java.
    // Jackson auto-converts a List into a JSON array on serialization.
    private List<String> types;

    // No custom constructor is used here — matches how the course
    // actually builds this object: create a blank instance with `new AddPlace()`,
    // then populate it field-by-field via setters. Jackson only strictly
    // requires the IMPLICIT no-arg constructor to exist (which every class
    // gets automatically unless you define ANY other constructor yourself).

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}