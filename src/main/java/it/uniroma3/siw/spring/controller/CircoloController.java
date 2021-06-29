package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Circolo;
import it.uniroma3.siw.spring.service.CircoloService;

@Controller
public class CircoloController {
	
	@Autowired
	private CircoloService circoloService;
	
    @Autowired
    private CircoloValidator circoloValidator;
        

    @RequestMapping(value="/addCircolo", method = RequestMethod.GET)
    public String addCircolo(Model model) {
    	model.addAttribute("circolo", new Circolo());
        return "circoloForm.html";
    }

    @RequestMapping(value = "/circolo/{id}", method = RequestMethod.GET)
    public String getCircolo(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("circolo", this.circoloService.circoloPerId(id));
    	return "circolo.html";
    }

    @RequestMapping(value = "/circolo", method = RequestMethod.GET)
    public String getCircoli(Model model) {
    		model.addAttribute("circoli", this.circoloService.tutti());
    		return "circoli.html";
    }
    
    @RequestMapping(value = "/circolo", method = RequestMethod.POST)
    public String newCircolo(@ModelAttribute("circolo") Circolo circolo, 
    									Model model, BindingResult bindingResult) {
    	this.circoloValidator.validate(circolo, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.circoloService.inserisci(circolo);
            model.addAttribute("circoli", this.circoloService.tutti());
            return "circoli.html";
        }
        return "circoloForm.html";
    }
}
