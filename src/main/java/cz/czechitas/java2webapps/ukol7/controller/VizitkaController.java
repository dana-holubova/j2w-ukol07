package cz.czechitas.java2webapps.ukol7.controller;

import cz.czechitas.java2webapps.ukol7.entity.Vizitka;
import cz.czechitas.java2webapps.ukol7.repository.VizitkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class VizitkaController {

    private final VizitkaRepository repository;

    @Autowired
    public VizitkaController(VizitkaRepository repository) {
        this.repository = repository;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     * Zobrazení seznamu vizitek
     */
    @GetMapping("/")
    public Object seznam() {
        Iterable<Vizitka> vsechnyVizitky = repository.findAll();
        return new ModelAndView("seznam")
                .addObject("seznam", vsechnyVizitky);
    }

    /**
     * Zobrazení jedné vizitky
     */
    @GetMapping("/{id:[0-9]+}")
    public Object detail(@PathVariable Integer id) {
        Optional<Vizitka> vizitka = repository.findById(id);
        if (vizitka.isPresent()) {
            return new ModelAndView("vizitka")
                    .addObject("vizitka", vizitka.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Zobrazení formuláře
     */
    @GetMapping("/nova")
    public Object nova() {
        return new ModelAndView("formular")
                .addObject("formular", new Vizitka());
    }

    /**
     * Přidání nové vizitky
     */
    @PostMapping("/nova")
    public Object pridat(@ModelAttribute("formular") @Valid Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/formular";
        }
        vizitka.setId(null);
        repository.save(vizitka);
        return "redirect:/";
    }

    /**
     * Smazání vizitky
     */
    @PostMapping(value = "/", params = "akce=smazat")
    public Object smazat(Vizitka vizitka) {
        repository.deleteById(vizitka.getId());
        return "redirect:/";
    }

/**
 * Úprava vizitky
 */
// TODO: Zkusit dokončit. Zatím nevím jak.
//@GetMapping (value = "/", params = "akce=upravit")
//    public String upravit() {
//    return "redirect:/formular";
//}
}
