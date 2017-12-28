package cn.et.food.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.food.entity.Food;
import cn.et.food.entity.Result;
import cn.et.food.service.FoodService;
import cn.et.food.utils.PageTools;



@Controller
public class FoodController {
	@Autowired
	FoodService service;
	//����json
	@ResponseBody
	@RequestMapping("/queryFood")
	public PageTools queryFood(String foodname,Integer page,Integer rows){	
		return service.queryFood(foodname,page,rows);
	}
	
	@ResponseBody
	@RequestMapping("/saveFood")
	public Result saveFood(Food food,MultipartFile myImage){
		Result r = new Result();
		r.setCode(1);
		try {
			
			//��ȡ�ļ���
			String fileName = myImage.getOriginalFilename();
			File destFile = new File("E:\\myImage\\"+fileName);
			//���ļ�����ָ��λ��
			myImage.transferTo(destFile);
			
			service.saveFood(food);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		
		return r;
	} 
	
	@ResponseBody
	@RequestMapping(value="/deleteFood/{foodid}",method=RequestMethod.DELETE)
	public Result deleteFood(@PathVariable String foodid,Integer page,Integer rows){
		Result r = new Result();
		r.setCode(1);
		
		String[] str=foodid.split(",");
		
		try {
			//�����?
			/*String str=null;
			str.toString();*/
			for(int i=0;i<str.length;i++){
				//ɾ��
				service.deleteFood(Integer.parseInt(str[i]));
			}
		
			
		} catch (Exception e) {
			//ɾ��ʧ��
			r.setCode(0);
			r.setMessage(e);		
		}
		
		return r;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/update/{foodid}",method=RequestMethod.PUT)
	public Result updateFood(@PathVariable Integer foodid,Food food,Integer page,Integer rows){
		food.setFoodid(foodid);
		Result r  = new Result();
		r.setCode(1);
		
		try {
			service.updateFood(food);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		
		return r;
	}
}
