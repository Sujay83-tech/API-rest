package pojo;

public class Courses {

    private Api api;
    private WebAutomation webAutomation;
    private Mobile mobile;

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public WebAutomation getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(WebAutomation webAutomation) {
        this.webAutomation = webAutomation;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public static class Api {
        // fields go here
    }

    public static class WebAutomation {
        // fields go here
    }

    public static class Mobile {
        // fields go here
    }
}