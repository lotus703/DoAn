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

import com.crm.entity.Status;
import com.crm.repository.StatusRepository;

@RestController
@RequestMapping("api/admin/status")
public class AdminStatusController {
	@Autowired // Tiêm StatusRepository vào để xài
	private StatusRepository statusRepository;

	@GetMapping("")
	public Object getAll() {
		List<Status> status = statusRepository.findAll();
		if (status == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Status>>(status, HttpStatus.OK);

	}

	@PostMapping("")
	public Object insert(@Valid @RequestBody Status status, BindingResult errors) {
		// Kiểm tra lỗi nhập liệu
		if (errors.hasErrors())
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		// tạo id cho status
		status.setId(UUID.randomUUID().toString());
		if (statusRepository.findByName(status.getName()) != null) {
			return new ResponseEntity<String>("Tên trạng thái đã tồn tại!", HttpStatus.BAD_REQUEST);
		}
		// Thực hiện hàm save thành công (Không có lỗi)
		if (statusRepository.save(status) != null) {
			return new ResponseEntity<Status>(status, HttpStatus.CREATED);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public Object update(@PathVariable("id") String id, @Valid @RequestBody Status status, BindingResult errors) {
		// Kiểm tra lỗi nhập liệu
		if (errors.hasErrors())
			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		status.setId(id);
		if (statusRepository.save(status) != null) {
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		// cập nhật thất bại
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable("id") String id) {
		// Thực hiện xóa role
		try {
			statusRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			// Xóa thất bại
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}
