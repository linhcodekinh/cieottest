package com.lima.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.lima.dto.BucketAndFileDTORequest;
import com.lima.service.S3FileService;

@RestController
@RequestMapping("/api/public/v1/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class S3FileController {

	private static final String MESSAGE_1 = "Uploaded the file successfully";
	private static final String FILE_NAME = "fileName";

	@Autowired
	S3FileService fileService;

//    @GetMapping
//    public ResponseEntity<Object> findByName(@RequestBody FileDTORequest fileDTORequest) {
//        return ResponseEntity
//                .ok()
//                .cacheControl(CacheControl.noCache())
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + fileDTORequest.getFileName() + "\"")
//                .body(new InputStreamResource(fileService.findByName(fileDTORequest.getFileName())));
//    }

//    @PostMapping
//    public ResponseEntity<Object> save(@RequestParam("file") MultipartFile multipartFile) {
//        fileService.save(multipartFile);
//        return new ResponseEntity<>(MESSAGE_1, HttpStatus.OK);
//    }

	@GetMapping
	// public ResponseEntity<Object> findByName(HttpServletRequest request,
	// @RequestBody(required = false) Map<String, String> params) {
	public ResponseEntity<String> findByName(@RequestParam("bucketKey") String bucketKey,
			@RequestParam("fileName") String fileName) {
		// final String path = request.getServletPath();
//		if (bucketAndFileDTORequest.getFileName().isEmpty() != false
//				|| !"".equals(bucketAndFileDTORequest.getFileName())
//						&& bucketAndFileDTORequest.getBucketKey().isEmpty() != false
//				|| !"".equals(bucketAndFileDTORequest.getBucketKey()))
//			return new ResponseEntity<>(fileService.findURLByName(bucketAndFileDTORequest.getBucketKey(),
//					bucketAndFileDTORequest.getFileName()), HttpStatus.OK);
		if (bucketKey.isEmpty() != false || !"".equals(bucketKey) && fileName.isEmpty() != false
				|| !"".equals(fileName)) {
			return new ResponseEntity<>(fileService.findURLByName(bucketKey, fileName), HttpStatus.OK);
		}
		return new ResponseEntity<>("Ko tim thay file name", HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<Object> genPresignedURL(@RequestParam("extension") String extension,
			@RequestParam("bucketKey") String bucketKey) {
		return new ResponseEntity<>(fileService.genPresignedURL(bucketKey, extension), HttpStatus.OK);
	}

}