package com.s3.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.s3.file.service.FileUploadService;

@RestController
@RequestMapping("svc/fileupload/v1")
public class FileUploadS3Controller {

	@Autowired
	FileUploadService fileUploadService;

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		return fileUploadService.uploadFile(file);
	}

	@GetMapping("/getObject")
	public String getObject(@RequestParam("fileName") String fileName) {
		return fileUploadService.getObject(fileName);
	}
}
