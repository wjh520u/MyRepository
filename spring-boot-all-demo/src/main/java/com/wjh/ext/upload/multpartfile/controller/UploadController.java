package com.wjh.ext.upload.multpartfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = null;

    @GetMapping("/")
    public String index() {
    	String path = this.getClass().getResource("").getPath();
    	System.out.println(path.substring(0,path.indexOf("classes")+8));
    	System.out.println(this.getClass().getResource("").getPath());;
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	System.out.println(this.getClass().getResource("").getPath());;
    	
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            if (UPLOADED_FOLDER == null) {
            	synchronized (this) {
					if (UPLOADED_FOLDER == null) {
						String path2 = this.getClass().getResource("").getPath();
		            	UPLOADED_FOLDER = path2.substring(1,path2.indexOf("classes")+8)+"commom/ext/files/".replace("/", File.separator);
					}
				}
			}
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}