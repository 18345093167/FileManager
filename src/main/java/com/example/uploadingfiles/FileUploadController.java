package com.example.uploadingfiles;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.example.uploadingfiles.common.FileUtils;
import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;

@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message","您成功上传了文件 " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	@RequestMapping("/file-upload-single")
	@ResponseBody
	public  synchronized String FileUploadSingle(HttpServletRequest request,@RequestParam("myFile") MultipartFile file, Model model) {

				
				if (file.isEmpty()) {
					return "上传失败，请选择文件";
				}
				String fileName = file.getOriginalFilename();
		
				File dest = null;
				String os = System.getProperty("os.name");
				System.out.println(os);
				HttpSession session = request.getSession();
				Object user = session.getAttribute("loginUser");

				if (os.toLowerCase().startsWith("win")) {
					String path = "D:"+File.separator+user+File.separator+"img"+File.separator;
					System.out.println(path);
					FileUtils.createNewFile(path + fileName);
					dest= new File(path + fileName);
				}else {
					String path = "/opt/userFolder/"+user+"/";
					System.out.println(path);
					FileUtils.createNewFile(path + fileName);
					dest= new File(path + fileName);
				}
				model.addAttribute("src","img/"+fileName);
				try {
					file.transferTo(dest);
					System.out.println("上传成功！");
					return JSON.toJSONString("上传成功！");
				} catch (IOException e) {
					System.out.println("上传失败！");
					return JSON.toJSONString("上传失败！");
				}
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/delete/{filename}")
	public String deleteFile(@PathVariable("filename") String filename,RedirectAttributes redirectAttributes) {
		storageService.deleteFile(filename);
		redirectAttributes.addFlashAttribute("message","您成功删除了文件 " + filename + "!");
		return "redirect:/";
	}
}
