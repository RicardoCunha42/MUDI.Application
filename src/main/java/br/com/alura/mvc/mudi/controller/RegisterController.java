package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.UserDto;
import br.com.alura.mvc.mudi.repository.UserRepository;
import br.com.alura.mvc.mudi.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	@GetMapping()
	public String getRegisterForm(Model model) {
		this.userService.getUserDto(model);

		return "register/registerForm";
	}

	@PostMapping("/save")
	public String createUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("userDto", userDto);
			return "register/registerForm";
		}

		this.userService.saveUser(userDto);
		return "redirect:/login";
	}
}
