package cars._security.controller.constants;

public interface AuthApi {
    // -------------Authorization and authentication---------------------------
    String AUTH = "/auth";
    String USER = "/user";
    String USERNAME = "/{username}";

    String REGISTRATION = "/registration";
    String LOGIN = "/login"; //  ALL with not authentication

    String ALL = "/all";
}