package guru.springframework.controllers;

import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index.html", "index"})
    public String getIndex(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
