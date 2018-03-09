package com.beta.crop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.beta.crop.dao.UserMapper;
import com.beta.crop.model.DataModel;
import com.beta.crop.model.ParameterPrototype;
import com.beta.crop.model.User;
import com.beta.crop.service.UserService;
import com.beta.crop.util.RedisUtil;
import com.beta.crop.util.ResultMapUtils;

import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	private static Jedis redis_instance = RedisUtil.getRedisIns();
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public DataModel<Object> Signin(JSONObject data) {
		// TODO Auto-generated method stub
		String phone = data.getString("phone");
		String pswd = data.getString("login_password");
		Example example = new Example(User.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("phone", phone).andEqualTo("loginPassword",pswd); 
		List<User> result = mapper.selectByExample(example);
		// 用户不存在 或 用户名密码错误
		if(result.size()==0) {
			return ResultMapUtils.getFailResultMap("201", "用户不存在或用户名密码错误");
		}
		
		
		User user = result.get(0);
		
		if(redis_instance.exists(user.getUserId())) {
			return ResultMapUtils.getFailResultMap("202", "用户已登录,最后登录时间 "+
					user.getLastSignInTime());
		}
		
		// 更新
		User update_template = new User();
		update_template.setId(user.getId());
		update_template.setLastDeviceId(data.getString("deviceid"));
		update_template.setLastSignInTime(new Date());
		update_template.setLastSignInIp(data.getString("ip"));
		
		int update_result = update(update_template);
		// 更新失败
		if(update_result==0) {
			return ResultMapUtils.failUpdating();
		}
		
		String login_token = UUID.randomUUID().toString();
		String user_josn = net.sf.json.JSONObject.fromObject(user).toString();
		Map<String, Object> map = (Map<String, Object>) JSON.parse(user_josn);
		map.put("token", login_token);
		redis_instance.set(user.getUserId(), login_token);
		redis_instance.expire(user.getUserId(), 3600*24*30);
		
		return ResultMapUtils.getResultMap("登录成功", map);
	}

	public DataModel<Object> Signout(ParameterPrototype params) {
		// TODO Auto-generated method stub
		String user_id = params.getUserid();
		if(!redis_instance.exists(user_id)) {
			return ResultMapUtils.getFailResultMap("201", "用户未登录");
		}
			redis_instance.del(user_id);
		
		return ResultMapUtils.getResultMap("登出成功", "");
	}

	public boolean Signup(JSONObject data) {
		// TODO Auto-generated method stub
		String pswd = data.getString("login_password");
		String phone = data.getString("phone");
		UserMapper mapper = (UserMapper) super.mapper;
		Example example = new Example(User.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("phone", phone);
		List<User> pre_check = mapper.selectByExample(example);
		if(pre_check.size()!=0) {
			return false;
		}
		User user = new User();
		user.setPhone(phone);
		user.setLoginPassword(pswd);
		user.setUserId(UUID.randomUUID().toString());
		int result = mapper.insert(user);
		
		return result==0?false:true;
	}

	public int update(User user) {
		// TODO Auto-generated method stub
		Example example = new Example(User.class);
		Criteria cri = example.createCriteria();
		cri.andEqualTo("id",user.getId());
		int result = mapper.updateByExampleSelective(user, example);
		
		return result;
	}
	




}
