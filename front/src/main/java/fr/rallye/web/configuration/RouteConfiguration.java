package fr.rallye.web.configuration;

import fr.rallye.web.constante.WebConstante;
import fr.rallye.web.tools.WebTools;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RouteConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        ajouterRedirectionVue(registry, "", WebConstante.PAGE_ACCUEIL);

        ajouterControleurVue(registry, WebConstante.PAGE_ACCUEIL);
    }

    private static void ajouterRedirectionVue(
            final ViewControllerRegistry registry, final String cheminAccess, final String pageTemplate) {
        registry.addRedirectViewController(WebTools.createPathWeb(WebTools.createPathWeb(cheminAccess))
                , WebTools.createPathWeb(pageTemplate));
    }

    private static void ajouterControleurVue(final ViewControllerRegistry registry, final String page) {
        registry.addViewController(WebTools.createPathWeb(page)).setViewName(page);
    }

}
