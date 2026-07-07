package pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Courses {

    private List<Api> api;
    private List<WebAutomation> webAutomation;
    private List<Mobile> mobile;

    public List<Api> getApi() { return api; }
    public void setApi(List<Api> api) { this.api = api; }

    public List<WebAutomation> getWebAutomation() { return webAutomation; }
    public void setWebAutomation(List<WebAutomation> webAutomation) { this.webAutomation = webAutomation; }

    public List<Mobile> getMobile() { return mobile; }
    public void setMobile(List<Mobile> mobile) { this.mobile = mobile; }
}