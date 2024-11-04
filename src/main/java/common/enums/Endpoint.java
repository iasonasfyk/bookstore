package common.enums;

public enum Endpoint {
    BOOKS("Books"),
    AUTHORS("Authors");

    private final String myEndpoint;
    Endpoint(String endpoint) {
        myEndpoint = endpoint;
    }
    public String get() {
        return myEndpoint;
    }
}
