package ru.nikita.library.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.nikita.library.dao.WatchDAO;
import ru.nikita.library.models.Watch;

@Controller
@RequestMapping("/watches")
public class WatchController {

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("lwatches", WatchDAO.index());
        return "watches/index";
    }

    @GetMapping("/{id}")
    public String showWatch(@PathVariable("id") int id, Model model) {
        model.addAttribute("watch", WatchDAO.findWatch(id));
        return "/watches/showWatch";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("watch") Watch watch) {
        return "watches/new";
    }

    @PostMapping()
    public String newWatch(@ModelAttribute("watch") @Valid Watch watch,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "watches/new";
        }
        WatchDAO.addNewWatch(watch);
        return "redirect:watches";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("watch", WatchDAO.findWatch(id));
        return "watches/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("watch") @Valid Watch watch, BindingResult bindingResult,
            @PathVariable("id") int id) {
        WatchDAO.updateWatch(id, watch);
        if (bindingResult.hasErrors()) {
            return "watches/new";
        }
        return "redirect:/watches";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        WatchDAO.deleteWatch(id);
        return "redirect:/watches";
    }

}