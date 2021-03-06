package fr.workshop.web.controleur;

import fr.workshop.api.AppelApi;
import fr.workshop.exception.ApiException;
import fr.workshop.exception.ObjetVidException;
import fr.workshop.tools.ControleObjet;
import fr.workshop.web.constante.WebConstante;
import fr.workshop.web.dto.LoginDto;
import fr.workshop.web.dto.factory.LoginDtoFactory;
import fr.workshop.web.exception.LoginDtoException;
import fr.workshop.web.tools.CookiesTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = {"/seConnecter"})
public class SeConnecterControleur {

    @ModelAttribute("loginDto")
    public LoginDto loginDto() {
        return LoginDtoFactory.getLoginDtoEmpty();
    }

    @GetMapping
    public ModelAndView getSeConnecter(HttpServletRequest req) {
        return new ModelAndView(WebConstante.PAGE_SE_CONNECTER);
    }

    @PostMapping
    public ModelAndView postSeConnecter(final Model model,
                                        @ModelAttribute("loginDto") final LoginDto loginDto,
                                        final HttpServletResponse response,
                                        HttpServletRequest req) {
        try {
            controleValeurNonRenseigner(model, loginDto);
            String codeToken = AppelApi.authentificationFromLoginPassword(loginDto);
            CookiesTools.createCookies(response,codeToken);
            return new ModelAndView(WebConstante.PAGE_AUTHENTIFICATION_REDIRECT);
        }catch (LoginDtoException | ApiException e){
            model.addAttribute("message", "Votre login ou votre mot de passe sont invalide.");
            return new ModelAndView(WebConstante.PAGE_SE_CONNECTER);
        }
    }

    static void controleValeurNonRenseigner(final Model model, final LoginDto loginDto) throws LoginDtoException {
        boolean invalide = false;
        try {
            ControleObjet.champNonVideString(loginDto.getLogin());
            model.addAttribute("valeur_login", loginDto.getLogin());
        } catch (final ObjetVidException e) {
            model.addAttribute("erreurLogin", "L'identifiant ne peut pas ??tre vide");
            invalide = true;
        }

        try {
            ControleObjet.champNonVideString(loginDto.getPassword());
        } catch (final ObjetVidException e) {
            model.addAttribute("erreurPassword", "Le mot de passe ne peut pas ??tre vide");
            invalide = true;
        }

        if (invalide) {
            throw new LoginDtoException("Un des champs n'est pas valide ?? la saisie");
        }
    }
}
