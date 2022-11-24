package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.UserDto;

@Controller
@RequestMapping("/register")
public class registerController {
	@GetMapping()
	public String getRegisterForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "register/registerForm";
	}
}
