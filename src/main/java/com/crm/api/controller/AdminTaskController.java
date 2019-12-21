package com.crm.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entity.Task;
import com.crm.repository.TaskRepository;
 
@RestController
@RequestMapping("api/admin/task")
public class AdminTaskController {
	@Autowired // Tiêm TaskRepository vào để xài
	private TaskRepository taskRepository;
	
	@GetMapping("")
	public Object getAll() {
		List<Task> tasks = taskRepository.findAll();
		// Nếu hàm findAll trả về null (có lỗi xảy ra ở tầng Repository)
		if (tasks == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

		}
		// Nếu hàm findAll trả về khác null (không có lỗi) thì trả về danh sách user
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@PostMapping("")
	public Object insert(@Valid @RequestBody Task task, BindingResult errors) {

		// Kiểm tra lỗi nhập liệu
		if (errors.hasErrors())
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        //tạo id cho task
		task.setId(UUID.randomUUID().toString());
		// Kiểm tra xem email tồn tại chưa (khác null là đã có trong db rồi)
		if (taskRepository.findByName(task.getName()) != null) {
			return new ResponseEntity<String>("Tên quyền đã tồn tại!", HttpStatus.BAD_REQUEST);
		}

		// Thực hiện hàm saveOrUpdate thành công (Không có lỗi)
		if (taskRepository.save(task) != null) {
			return new ResponseEntity<Task>(task, HttpStatus.CREATED);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public Object update(@PathVariable("id") String id, @Valid @RequestBody Task task, BindingResult errors) {

		// Kiểm tra lỗi nhập liệu
		if (errors.hasErrors())
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);

		// Thực hiện hàm saveOrUpdate thành công (Không có lỗi)
		task.setId(id);
		if (taskRepository.save(task) != null) {
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		}

		// Cập nhật thất bại
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") String id) {

		// Thực hiện xóa role
		try {
			taskRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			// Xóa thất bại
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}
