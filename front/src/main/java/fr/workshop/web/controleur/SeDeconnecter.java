package fr.workshop.web.controleur;

import fr.workshop.api.AppelApi;
import fr.workshop.exception.ApiException;
import fr.workshop.web.constante.WebConstante;
import fr.workshop.web.exception.CookiesException;
import fr.workshop.web.tools.CookiesTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = {"/seDeconnecter"})
public class SeDeconnecter {

    @GetMapping
    public ModelAndView getSeDeconnecter(final Model model, final HttpServletResponse res, final HttpServletRequest req) {
        try{
            String nameCookie = CookiesTools.readCookies(req);
            CookiesTools.deleteCookies(res);
            AppelApi.deleteCookies(nameCookie);
            model.addAttribute("message", "Vous êtes maintenant déconnecter");
            return new ModelAndView(WebConstante.PAGE_SE_DECONNECTER);
        }catch (CookiesException | ApiException e){
            model.addAttribute("message","une erreur est survenue");
            return new ModelAndView(WebConstante.PAGE_ACCUEIL);
        }

    }
}
