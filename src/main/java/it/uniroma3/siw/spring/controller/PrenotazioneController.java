package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@Controller
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
    @Autowired
    private PrenotazioneValidator prenotazioneValidator;
    

    @RequestMapping(value="/addPrenotazione", method = RequestMethod.GET)
    public String addPrenotazione(Model model) {
    	model.addAttribute("prenotazione", new Prenotazione());
        return "prenotazioneForm.html";
    }

    @RequestMapping(value = "/prenotazione/{id}", method = RequestMethod.GET)
    public String getPrenotazione(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("prenotazione", this.prenotazioneService.prenotazionePerId(id));
    	return "prenotazione.html";
    }

    @RequestMapping(value = "/prenotazione", method = RequestMethod.GET)
    public String getPrenotazioni(Model model) {
    		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
    		return "prenotazioni.html";
    }
    
    @RequestMapping(value = "/prenotazione", method = RequestMethod.POST)
    public String newPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, 
    									Model model, BindingResult bindingResult) {
    	this.prenotazioneValidator.validate(prenotazione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	if(prenotazioneService.getCampoService().IsDisponibile(prenotazione.getCampo(), 
        					prenotazione.getData(), prenotazione.getOraInizio(), prenotazione.getOraFine())) {
        	this.prenotazioneService.inserisci(prenotazione);
            model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
            return "prenotazioni.html";
        	}else {
        		return "prenotazioneErrore.html";
        	}
        }
        return "prenotazioneForm.html";
    }
     
}
