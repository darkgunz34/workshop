package fr.workshop.web.controleur;

import fr.workshop.api.AppelApi;
import fr.workshop.exception.ApiException;
import fr.workshop.web.constante.WebConstante;
import fr.workshop.web.exception.CookiesException;
import fr.workshop.web.tools.CookiesTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = { "/authentification" })
public class AuthentificationControleur {

    @GetMapping
    public ModelAndView getSeConnecter(HttpServletRequest req) {
        try{
            String token = CookiesTools.readCookies(req);
            AppelApi.authentificationFromToken(token);
        }catch (CookiesException | ApiException e){
            return new ModelAndView(WebConstante.PAGE_ACCUEIl_REDIRECT);
        }
        return new ModelAndView(WebConstante.PAGE_AUTHENTIFICATION);
    }
}
