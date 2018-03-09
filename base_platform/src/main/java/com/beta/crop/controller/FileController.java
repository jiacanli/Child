package com.beta.crop.controller;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.beta.crop.model.DataModel;
import com.beta.crop.util.Constant;
import com.beta.crop.util.PropertiesFileUtil;
import com.beta.crop.util.ResultMapUtils;

@RestController
@RequestMapping("/file")
public class FileController {
	private static Properties pUtil = PropertiesFileUtil.LoadProperties("general.properties");
	private static String user_dir = pUtil.getProperty("user_logo_dir");
	private static String product_dir = pUtil.getProperty("product_dir");
	private static String company_dir = pUtil.getProperty("company_certification_dir");
	public FileController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("/service")
	public DataModel<Object> upload(HttpServletRequest request,@RequestParam("file") MultipartFile file){
		String code = request.getParameter("code");
		String guid = request.getParameter("guid");
		if(code==null||guid==null) {
			return ResultMapUtils.getFailResultMap(Constant.FileConstant.WRONG_PARAMETER, "参数解析错误");
		}
		
		int branches = Integer.parseInt(code);
		try {
		switch(branches) {
		case 1: // 用户头像
			File user_logo = new File(user_dir+guid+".jpg");
			file.transferTo(user_logo);
			break;
		case 2: // 产品展示照片
			File product = new File(product_dir+guid+".jpg");
			file.transferTo(product);
			break;
		case 3: // 企业执照
			File company = new File(company_dir+guid+".jpg");
			file.transferTo(company);
			break;
		default:
			return ResultMapUtils.getFailResultMap(Constant.FileConstant.WRONG_PARAMETER, "code参数解析失败");
		}
			return ResultMapUtils.getResultMap("上传成功", "");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResultMapUtils.getFailResultMap(Constant.FileConstant.UPLOAD_FAIL, "上传文件失败");
		}
		
	}

}
