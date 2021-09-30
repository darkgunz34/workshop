package group.workshop.configuration;

public final class WebTools {

    private WebTools() {

    }

    public static String createPathWeb(final String page) {
        return "/".concat(page);
    }
}
