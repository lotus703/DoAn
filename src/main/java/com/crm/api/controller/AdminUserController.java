package com.crm.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entity.User;
import com.crm.repository.UserRepository;
 
@RestController
@RequestMapping("api/admin/user")
public class AdminUserController {
	@Autowired // Tiêm UserRepository vào để xài
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("")
	public Object getAll() {
		List<User> users = userRepository.findAll();
		// Nếu hàm findAll trả về null (có lỗi xảy ra ở tầng Repository)
		if (users == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

		}
		// Nếu hàm findAll trả về khác null (không có lỗi) thì trả về danh sách user
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("")
	public Object insert(@Valid @RequestBody User user, BindingResult errors) {

		// Kiểm tra lỗi nhập liệu
		if (errors.hasErrors())
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);

		// Kiểm tra xem email tồn tại chưa (khác null là đã có trong db rồi)
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return new ResponseEntity<String>("Email đã tồn tại!", HttpStatus.BAD_REQUEST);
		}
		//tạo id cho role
				user.setId(UUID.randomUUID().toString());
				user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Thực hiện hàm saveOrUpdate thành công (Không có lỗi)
		if (userRepository.save(user) != null) {
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public Object update(@PathVariable("id") String id, @Valid @RequestBody User user, BindingResult errors) {

		// Kiểm tra lỗi nhập liệu
		if (errors.hasErrors())
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);

		// Thực hiện hàm saveOrUpdate thành công (Không có lỗi)
		user.setId(id);
		
		if (userRepository.save(user) != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		// Cập nhật thất bại
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") String id) {

		// Thực hiện xóa user
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			// Xóa thất bại
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		
	}

}
