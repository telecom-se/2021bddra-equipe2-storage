package fr.brichie.brichie.controller;
import fr.brichie.brichie.model.*;
import fr.brichie.brichie.model.data;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
@Controller
public class ControllerSource {

    @GetMapping("/")
    public String greetingForm(Model model) {
      model.addAttribute("data", new data());
      return "index";
    }

    @PostMapping("/")
    public String greetingSubmit(@ModelAttribute("data") data data, Model model) {
        data.refresh();       
      model.addAttribute("data", data);
      return "res";
    
    }
    /*@GetMapping("/")
    public String brichie(Model m) 
    {

        m.addAttribute("clem",brichie);
        
        return "index";
    }

    @GetMapping("/Database")
    public String Database()
    {
        brichie = getAllDB();

        return "redirect:/";
    }

    @RequestMapping(value="/Serie", method=RequestMethod.GET)
    public String Serie(@RequestParam(name="action", required=true) String action) {
        
        Series = getSeries(action);
        System.out.println("Action : " + action);
        return "redirect:/";
    }*/
    
}