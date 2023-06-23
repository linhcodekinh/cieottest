package com.lima.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lima.service.FileService;

@RestController
@RequestMapping("/api/public/v1/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

//    private static final String MESSAGE_1 = "Uploaded the file successfully";
	private static final String FILE_NAME = "fileName";

	@Autowired
	FileService fileService;
//
//    @GetMapping
//    public ResponseEntity<Object> findByName(@RequestBody(required = false) Map<String, String> params) {
//        return ResponseEntity
//                .ok()
//                .cacheControl(CacheControl.noCache())
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + params.get(FILE_NAME) + "\"")
//                .body(new InputStreamResource(fileService.findByName(params.get(FILE_NAME))));
//
//    }
//
//    @PostMapping
//    public ResponseEntity<Object> save(@RequestParam("file") MultipartFile multipartFile) {
//        fileService.save(multipartFile);
//        return new ResponseEntity<>(MESSAGE_1, HttpStatus.OK);
//    }

	@GetMapping
	public ResponseEntity<Object> findByName(HttpServletRequest request,
			@RequestBody(required = false) Map<String, String> params) {
		final String path = request.getServletPath();
		if (params.containsKey(FILE_NAME))
			return new ResponseEntity<>(fileService.findByName(params.get(FILE_NAME)), HttpStatus.OK);
		return new ResponseEntity<>("Ko tim thay file name", HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<Object> saveFile(@RequestParam("extension") String extension) {
		return new ResponseEntity<>(fileService.save(extension), HttpStatus.OK);
	}

}