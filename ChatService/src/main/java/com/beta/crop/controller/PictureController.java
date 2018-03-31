package com.beta.crop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.beta.crop.model.DataModel;
import com.beta.crop.util.PropertiesFileUtil;
import com.beta.crop.util.ResultMapUtils;

@RestController
@RequestMapping("/picture")
public class PictureController {
	private static Properties pUtil = PropertiesFileUtil.LoadProperties("general.properties");
	private static String pic_path = pUtil.getProperty("picture_dir");
	public PictureController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@RequestMapping("/up")
	public DataModel<Object> PictureUpload(@RequestParam(required=true,value="pic") MultipartFile[] file){
		
		if(file==null||file.length==0){
			return ResultMapUtils.getFailResultMap(ResultMapUtils.PIC_UPLOAD_ERROR_KEY, 
                   ResultMapUtils.PIC_UPLOAD_ERROR);
		}
		for(MultipartFile item : file){
			File pic = new File(pic_path+item.getOriginalFilename());
			try {
				item.transferTo(pic);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResultMapUtils.getFailResultMap(ResultMapUtils.PIC_UPLOAD_ERROR_KEY, 
		                   ResultMapUtils.PIC_UPLOAD_ERROR);
			} 
		}
		
		return ResultMapUtils.getResultMap("上传成功", "");
		
	}
	
	@RequestMapping("/down")
	public ResponseEntity<byte[]> PictureDownload(HttpServletRequest request){
		String guid = request.getParameter("guid");
		if(guid==null){
			return null;
		}		
        HttpHeaders headers = new HttpHeaders();    
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", guid);    
		
		try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(pic_path+guid)),    
			        headers, HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

}
