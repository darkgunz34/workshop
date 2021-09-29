package fr.workshop.web.controleur;

import fr.workshop.web.constante.WebConstante;
import fr.workshop.web.tools.CookiesTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = {"/seDeconnecter"})
public class SeDeconnecter {

    @GetMapping
    public ModelAndView getSeDeconnecter(final Model model, HttpServletResponse res) {
        model.addAttribute("message", "Vous êtes maintenant déconnecter");
        CookiesTools.deleteCookies(res);
        return new ModelAndView(WebConstante.PAGE_SE_DECONNECTER);
    }
}
