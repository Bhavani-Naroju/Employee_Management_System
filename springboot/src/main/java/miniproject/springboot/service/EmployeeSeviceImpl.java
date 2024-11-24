
package miniproject.springboot.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import miniproject.springboot.emailService.EmailService;
import miniproject.springboot.model.Employee;
import miniproject.springboot.repository.EmployeeRepository;
@Service
public class EmployeeSeviceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmailService emailService;
	@Override
	public List < Employee > getAllEmployees() {
		return employeeRepository.findAll();
	}
	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
		emailService.sendWelcomeEmail(employee.getEmail(), employee.getFirstName());
	}
	@Override
	public Employee getEmployeeById(long id) {
		Optional < Employee > optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
		employee = optional.get(); } else {
		throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}
	@Override
	public void deleteEmployeeById(long id) {
		Employee employee = getEmployeeById(id);
		this.employeeRepository.deleteById(id);
		emailService.sendFarewellEmail(employee.getEmail(), employee.getFirstName());
	}
}