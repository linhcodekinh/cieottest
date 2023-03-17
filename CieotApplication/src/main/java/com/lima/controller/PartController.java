package com.lima.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lima.dto.PartDTO;
import com.lima.payload.request.PartDTORequest;
import com.lima.payload.request.PartDTOWithFileRequest;
import com.lima.service.IPartService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class PartController {

	@Autowired
	private IPartService partService;

	@GetMapping("/hello2")
	public String hello() {
		return "hello";
	}

	// GET ALL
	@GetMapping("/part")
	public ResponseEntity<List<PartDTO>> getAll() {
		List<PartDTO> listPart = partService.getAll();
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	// GET part detail by part id
	@GetMapping("/part/{id}")
	public PartDTO getPartDetail(@PathVariable Integer id) {
		PartDTO partDTO = partService.getPartDetail(id);
		return partDTO;
	}

	// GET list by code and part no
	@GetMapping("/part/list-by-code-part-no")
	public ResponseEntity<Map<String, List<PartDTO>>> getAllByCodePartNo(
			@RequestParam(name = "partNo", required = false, defaultValue = "") Integer partNo) {
		Map<String, List<PartDTO>> maplistPart = partService.getAllByCodePartNo(partNo);
		return new ResponseEntity<>(maplistPart, HttpStatus.OK);
	}

	@GetMapping("/part/list-by-part-no")
	public ResponseEntity<List<PartDTO>> getAllByPartNo(
			@RequestParam(name = "partNo", required = false, defaultValue = "") Integer partNo) {
		List<PartDTO> listPart = partService.getAllByPartNo(partNo);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@GetMapping("/part/list-by-code")
	public ResponseEntity<List<PartDTO>> getAllByCode(
			@RequestParam(name = "code", required = false, defaultValue = "") Integer code) {
		List<PartDTO> listPart = partService.getAllByCode(code);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@GetMapping("/part/list-by-level")
	public ResponseEntity<List<PartDTO>> getAllByLevel(@RequestParam(name = "level", required = false) Integer level) {
		List<PartDTO> listPart = partService.getAllByLevel(level);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@PutMapping("/part/{id}")
	public ResponseEntity<PartDTO> update(@PathVariable Integer id, @RequestBody PartDTORequest partDTORequest) {
		PartDTO partDTO = partService.update(id, partDTORequest);
		return new ResponseEntity<>(partDTO, HttpStatus.OK);
	}

	@PostMapping("/part")
	public ResponseEntity<PartDTO> create(@RequestBody PartDTORequest partDTORequest) {
		PartDTO partDTO = partService.create(partDTORequest);
		return new ResponseEntity<>(partDTO, HttpStatus.OK);

	}

	@PostMapping(value = "/partByExcel", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<PartDTOWithFileRequest> createByExcelFile(@RequestPart("partDetail") String partDetail,
			@RequestPart("photoFile") MultipartFile photoFile, @RequestPart("audioFile") MultipartFile audioFile,
			@RequestPart("questionExcelFile") MultipartFile questionExcelFile)
			throws IllegalStateException, IOException {

		PartDTOWithFileRequest partDTOWithFileRequest = partService.getJson(partDetail, photoFile, audioFile,
				questionExcelFile);

		PartDTO partDTO = partService.createByExcelFile(partDTOWithFileRequest);

		System.out.println("file audioFile: " + partDTOWithFileRequest.getPhotoFile().getOriginalFilename());
		System.out.println("file photoFile: " + partDTOWithFileRequest.getAudioFile().getOriginalFilename());
		System.out.println(
				"file questionExcelFile: " + partDTOWithFileRequest.getQuestionExcelFile().getOriginalFilename());

		return new ResponseEntity<>(partDTOWithFileRequest, HttpStatus.OK);
	}

	// delete by id
	@PatchMapping("/part/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		partService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
