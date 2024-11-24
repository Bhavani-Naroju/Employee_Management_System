
package miniproject.springboot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import miniproject.springboot.model.Admin;
import miniproject.springboot.repository.AdminRepository;

import java.util.regex.Pattern;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public void saveAdmin(Admin admin) {
		String firstName = admin.getName().toLowerCase();;
		String email = admin.getEmail().toLowerCase();;
		// Create a pattern to match the email format with the first name
		// Email must start exactly with the first name and follow @admin.ac.in
		String emailPattern = "^" + Pattern.quote(firstName) + "@admin\\.ac\\.in$";
		if (Pattern.matches(emailPattern, email)) {
			adminRepository.save(admin);
		} else {
			throw new IllegalArgumentException("The email is either not in the required format or not starting with your name");
		}
	}
	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
}