package com.beta.crop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.mybatis.generator.api.dom.java.Field;
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
@RequestMapping("/sound")
public class SoundController {
	private static Properties pUtil = PropertiesFileUtil.LoadProperties("general.properties");
	private static String sound_path = pUtil.getProperty("sound_dir");
	public SoundController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("/up")
	public DataModel<Object> upload(@RequestParam(required=true,value="sound") MultipartFile[] file){
		if(file==null||file.length==0){
			return ResultMapUtils.getFailResultMap(ResultMapUtils.SOUND_UPLOAD_ERROR_KEY, 
					ResultMapUtils.SOUND_UPLOAD_ERROR);
		}
		
		for(MultipartFile item : file){
			File sound = new File(sound_path+item.getOriginalFilename());
			try {
				item.transferTo(sound);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResultMapUtils.getFailResultMap(ResultMapUtils.SOUND_UPLOAD_ERROR_KEY, 
						ResultMapUtils.SOUND_UPLOAD_ERROR);
			}
		}
		return ResultMapUtils.getResultMap("上传成功", "");
		
	}
	
	public ResponseEntity<byte[]> download(HttpServletRequest request){
		String guid = request.getParameter("guid");
		if(guid==null){
			return null;
		}		
        HttpHeaders headers = new HttpHeaders();    
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", guid);    
		
		try {
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(sound_path+guid)),    
			        headers, HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	
	
}
