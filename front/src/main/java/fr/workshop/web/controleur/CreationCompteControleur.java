package fr.workshop.web.controleur;

import fr.workshop.api.AppelApi;
import fr.workshop.exception.ApiException;
import fr.workshop.exception.ObjetVidException;
import fr.workshop.tools.ControleObjet;
import fr.workshop.web.constante.WebConstante;
import fr.workshop.web.dto.CreationCompteDto;
import fr.workshop.web.dto.factory.CreationCompteDtoFactory;
import fr.workshop.web.exception.LoginDtoException;
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
@RequestMapping(value = { "/creerUnCompte" })
public class CreationCompteControleur {

    @ModelAttribute("creationCompteDto")
    public CreationCompteDto creationCompteDto() {
        return CreationCompteDtoFactory.getCreationCompteDtoEmpty();
    }

    @GetMapping
    public ModelAndView getSeConnecter(HttpServletRequest req) {
        return new ModelAndView(WebConstante.PAGE_CREATION_COMPTE);
    }

    @PostMapping
    public ModelAndView postSeConnecter(final Model model,
                                        @ModelAttribute("creationCompteDto") final CreationCompteDto creationCompteDto,
                                        final HttpServletResponse response,
                                        HttpServletRequest req) {
        try {
            controleValeurNonRenseigner(model, creationCompteDto);
            AppelApi.creationCompte(creationCompteDto);
            return new ModelAndView(WebConstante.PAGE_ACCUEIL);
        }catch (LoginDtoException | ApiException e){
            model.addAttribute("message","Une erreur technique est survenue");
            return new ModelAndView(WebConstante.PAGE_CREATION_COMPTE);
        }
    }

    static void controleValeurNonRenseigner(final Model model, final CreationCompteDto creationCompteDto)
            throws LoginDtoException {
        boolean invalide = false;
        try {
            ControleObjet.champNonVideString(creationCompteDto.getLogin());
            model.addAttribute("valeur_login", creationCompteDto.getLogin());
        } catch (final ObjetVidException e) {
            model.addAttribute("erreurLogin", "L'identifiant ne peut pas être vide");
            invalide = true;
        }

        try {
            ControleObjet.champNonVideString(creationCompteDto.getMail());
            model.addAttribute("valeur_mail", creationCompteDto.getMail());
        } catch (final ObjetVidException e) {
            model.addAttribute("erreurMail", "L'adresse mail ne peut pas être vide'");
            invalide = true;
        }

        try {
            ControleObjet.champNonVideString(creationCompteDto.getPassword());
        } catch (final ObjetVidException e) {
            model.addAttribute("erreurPassword", "Le mot de passe ne peut pas être vide");
            invalide = true;
        }

        try {
            ControleObjet.champNonVideString(creationCompteDto.getPasswordConfirme());
            ControleObjet.champIdentiqueString(creationCompteDto.getPassword(),creationCompteDto.getPasswordConfirme());
        } catch (final ObjetVidException e) {
            model.addAttribute("erreurPasswordConfirme", "Les mots de passe doivent être identique");
            invalide = true;
        }

        if (invalide) {
            throw new LoginDtoException("Un des champs n'est pas valide à la saisie");
        }
    }
}
