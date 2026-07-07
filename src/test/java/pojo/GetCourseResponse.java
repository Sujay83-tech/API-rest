package pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCourseResponse {

    private List<GetCourse> courses;

    public List<GetCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<GetCourse> courses) {
        this.courses = courses;
    }
}