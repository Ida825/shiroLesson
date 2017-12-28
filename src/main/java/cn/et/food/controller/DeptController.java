package cn.et.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;

@Controller
public class DeptController {
	@Autowired
	DeptService service;
	@ResponseBody
	@RequestMapping("/queryDept")
	public  List<TreeNode> queryDept(Integer id){
		System.out.println(id+"--------------------------------------------------------------------------");
		if(id==null){
			id=0;
		}
		return service.queryTreeNode(id);
	}
	
	
}
