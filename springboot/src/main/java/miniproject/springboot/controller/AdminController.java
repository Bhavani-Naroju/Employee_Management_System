
package miniproject.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import miniproject.springboot.model.Admin;
import miniproject.springboot.service.AdminService;
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping("/signup")
	public String showSignupForm(Model model) {
		model.addAttribute("admin", new Admin());
		return "signup"; 
	}
	@PostMapping("/signup")
	public String registerAdmin(@ModelAttribute("admin") Admin admin,
	RedirectAttributes redirectAttributes) {
		try {
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			adminService.saveAdmin(admin);
			redirectAttributes.addFlashAttribute("message", "Admin registered successfully.");
			return "redirect:/login";
		} 
		catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/signup";
		} 
	}
	@GetMapping("/login")
	public String showLoginForm() {
	return "login"; 
	}
	
}