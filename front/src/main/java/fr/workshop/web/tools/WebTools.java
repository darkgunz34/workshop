package fr.rallye.web.tools;

public final class WebTools {

    private WebTools() {

    }

    public static String createPathWeb(final String page) {
        return "/".concat(page);
    }
}
