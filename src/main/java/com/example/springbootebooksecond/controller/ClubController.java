package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.dto.ClubDto;
import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.Club;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.service.ClubService;
import com.example.springbootebooksecond.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;
    private final UserService userService;

    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping
    private String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);

        return "clubs/clubs-list";
    }

    //create
    @GetMapping("/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs/clubs-create";
    }

    @PostMapping("/new")
    public String saveClub(@ModelAttribute("club") ClubDto clubDto) {
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    //update
    @GetMapping("/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs/clubs-edit";
    }

    @PostMapping("/{clubId}/edit")
    public String editClub(@PathVariable("clubId") long clubId,
                           @Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "clubs/clubs-edit";
        }

        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    //Detail Page
    @GetMapping("/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs/clubs-detail";
    }

    //Delete
    @GetMapping("/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    //searching
    @GetMapping("/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs/clubs-list";
    }
}
