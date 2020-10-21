package com.customer.controller;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbDao.ProfileDao;
import com.profileDTO.ProfileDTO;

@Controller
public class CustomerController {
	@Autowired
	private ProfileDao profileDao;

	@GetMapping("/signup")
	public String registers() {
		return "register";
	}

	@PostMapping("/signup")
	public String register(@ModelAttribute ProfileDTO profileDTO, Model model) {
		String password = PasswordGenerate.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());

		int i = profileDao.signup(profileDTO);
		if (i != 0) {
			model.addAttribute("msg", "Hi " + profileDTO.getName() + ", you have successfully signed up.");
		} else {
			model.addAttribute("msg", "Error Occured! Try Again");
		}

		return "login";
	}

	@GetMapping("/delete")
	public String DeleteData(@RequestParam("email") String email, Model model) {

		int i = profileDao.delete(email);
		if (i != 0) {
			model.addAttribute("success", "Successfully Deleted");
		} else {
			model.addAttribute("Success", "Error Occured");
		}

		return "redirect:/displayprofile";

	}

	@GetMapping("/displayprofile")
	public String DisplayProfile(Model model) {

		ArrayList<ProfileDTO> users = profileDao.profile();
		ArrayList<String> qual = (ArrayList<String>) profileDao.qual();

		model.addAttribute("qual", qual);
		model.addAttribute("data", users);

		return "wall";

	}

	@GetMapping("/editData")

	public String EditData(@RequestParam("email") String email, Model model) {

		ProfileDTO profileDTO = profileDao.edit(email);
		model.addAttribute("user", profileDTO);
		return "editdata";
	}

	@GetMapping("/loggedUser")

	public String LoggedUser(Model model) {
		Set<ProfileDTO> loggedUsers = ProfileDTO.loggedInUser();
		model.addAttribute("data", loggedUsers);
		return "loggedUser";

	}

	@GetMapping("/manage")

	public String manage() {
		return "manage";
	}

	@PostMapping("/qualfilter")

	public String qualFilter(@RequestParam("qualification") String qual, Model model) {

		ArrayList<ProfileDTO> users = profileDao.qualfilter(qual);
		ArrayList<String> q = (ArrayList<String>) profileDao.qual();

		model.addAttribute("qual", q);
		model.addAttribute("data", users);
		return "wall";

	}

	@PostMapping("/search")
	public String searchServ(@RequestParam("search") String search, Model model) {

		ArrayList<ProfileDTO> users = profileDao.dbsearch(search);
		ArrayList<String> qual = (ArrayList<String>) profileDao.qual();
		model.addAttribute("qual", qual);
		model.addAttribute("data", users);
		return "wall";

	}

	@GetMapping("/sort")
	public String sortProfile(@RequestParam("order") String sort, Model model) {
		ArrayList<ProfileDTO> users = profileDao.sort(sort);
		ArrayList<String> qual = (ArrayList<String>) profileDao.qual();

		model.addAttribute("qual", qual);
		model.addAttribute("data", users);
		return "wall";

	}

	@PostMapping("/update1")
	public String updateData(@ModelAttribute ProfileDTO profileDTO, Model model) {
		int a = profileDao.update(profileDTO);
		if (a != 0) {
			model.addAttribute("success", "Updated Successfully");
		} else {
			model.addAttribute("success", "Updating Error!! Please Try Again.");

		}
		return "redirect:/displayprofile";

	}

	@GetMapping("/signupImage")
	public String imagesign() {
		return "signupImage";
	}

	@PostMapping("/signupImage")
	public String imageSingUp(@ModelAttribute ProfileDTO profileDTO, Model model) {

		String password = PasswordGenerate.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());

		int i = profileDao.registerNew(profileDTO);
		if (i != 0) {
			model.addAttribute("msg", "Hi " + profileDTO.getName() + ", you have successfully signed up.");
		} else {
			model.addAttribute("msg", "Error Occured! Try Again");
		}

		return "login";
	}
}
