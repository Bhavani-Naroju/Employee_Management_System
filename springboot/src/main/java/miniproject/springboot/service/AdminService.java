
package miniproject.springboot.service;
import miniproject.springboot.model.Admin;

public interface AdminService {
	void saveAdmin(Admin admin);
	Admin findByEmail(String email);
}