package fr.workshop.web.controleur;

import fr.workshop.web.constante.WebConstante;
import fr.workshop.web.tools.CookiesTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/accueil"})
public class AccueilControleur {
    @GetMapping
    public ModelAndView getAccueil(HttpServletRequest req, Model model) {
        if(CookiesTools.checkClientConnecter(req)){
            model.addAttribute("client","true");
        }else{
            model.addAttribute("client","false");
        }
        return new ModelAndView(WebConstante.PAGE_ACCUEIL)
        ;
    }

}
