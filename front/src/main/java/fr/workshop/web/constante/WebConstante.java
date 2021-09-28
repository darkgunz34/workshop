package fr.workshop.web.constante;

public final class WebConstante {

    private static final String REDIRECT = "redirect:/";

    public static final String PAGE_ACCUEIL = "accueil";
    public static final String PAGE_ACCUEIl_REDIRECT = REDIRECT.concat(PAGE_ACCUEIL);

    public static final String PAGE_SE_CONNECTER = "seConnecter";

    public static final String PAGE_AUTHENTIFICATION = "authentification";
    public static final String PAGE_AUTHENTIFICATION_REDIRECT = REDIRECT.concat(PAGE_AUTHENTIFICATION);

    private WebConstante() {

    }
}
