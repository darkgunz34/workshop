package fr.workshop.web.controleur;

import fr.workshop.web.constante.WebConstante;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/", "/accueil"})
public class AccueilControleur {

    @GetMapping
    public ModelAndView getAccueil() {
        return new ModelAndView(WebConstante.PAGE_ACCUEIL);
    }
}
