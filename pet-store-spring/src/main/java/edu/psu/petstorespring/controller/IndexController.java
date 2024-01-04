package edu.psu.petstorespring.controller;

import edu.psu.petstorespring.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.psu.petstorespring.service.impl.PetService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String home(Model model){
        List<Pet> pets = petService.getPets();
        model.addAttribute("pets", pets);
        return "home";
    }

    @PostMapping("/search")
    public String indexFiltered(Model model, @RequestParam String searchParam) {
        List<Pet> pets = petService.searchPetsByType(searchParam);
        model.addAttribute("pets", pets);
        return "home";
    }

    @GetMapping("/add-pet")
    public String addPetPage() {
        return "addPet";
    }

    @PostMapping("/add-pet")
    public String submitAddPet(Model model, @RequestParam String name, @RequestParam String type, @RequestParam Double weight, @RequestParam Integer age, @RequestParam("image") MultipartFile file) {
        Pet newPet = new Pet(null, name, type, weight, age);
        petService.addPet(newPet, file);
        List<Pet> pets = petService.getPets();
        model.addAttribute("pets", pets);
        return "home";
    }

    @GetMapping("/edit-pet")
    public String editPetPage() {
        return "editPet";
    }

    @GetMapping("/edit-pet/{petId}")
    public String editPet(Model model, @PathVariable Long petId) {
        Pet pet = petService.getPetById(petId);
        model.addAttribute("pet", pet);
        return "editPet";
    }

    @PostMapping("/edit-pet")
    public String submitEditPet(Model model
            , @RequestParam String name
            , @RequestParam String type
            , @RequestParam Double weight
            , @RequestParam Integer age
            , @RequestParam Long petId
    ) {
        petService.editPet(name, type, weight, age, petId);
        List<Pet> pets = petService.getPets();
        model.addAttribute("pets", pets);
        return "home";
    }

    @GetMapping("/delete-pet/{petId}")
    public String deleteWatch(Model model, @PathVariable Long petId) {
        petService.deletePet(petId);
        List<Pet> pets = petService.getPets();
        model.addAttribute("pets", pets);
        return "home";
    }

    @PostMapping("/login")
    public String postLogin(Model model) {
        List<Pet> pets = petService.getPets();
        model.addAttribute("pets", pets);
        return "home";
    }
}
