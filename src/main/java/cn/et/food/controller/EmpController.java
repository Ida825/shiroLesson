package cn.et.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.entity.Result;
import cn.et.food.service.EmpService;
import cn.et.food.utils.PageTools;



@Controller
public class EmpController {
	@Autowired
	EmpService service;

	@ResponseBody
	@RequestMapping(value="/queryEmp",method=RequestMethod.GET)
	public PageTools queryEmp(Integer deptid,String ename,Integer page,Integer rows){	
		if(deptid!=null){
			return service.queryEmpByDeptid(deptid,page,rows);
		}
		return service.queryEmp(ename,page,rows);
	}
	
	
	@ResponseBody
	@RequestMapping("/saveEmp")
	public Result saveEmp(Emp emp){
		Result r = new Result();
		r.setCode(1);
		try {	
			service.saveEmp(emp);
			
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		
		return r;
	} 
	
	
	@ResponseBody
	@RequestMapping(value="/deleteEmp/{id}",method=RequestMethod.DELETE)
	public Result deleteEmp(@PathVariable String id,Integer page,Integer rows){
		Result r = new Result();
		r.setCode(1);
		
		String[] str=id.split(",");
		
		try {		
			for(int i=0;i<str.length;i++){
				//ɾ��
				service.deleteEmp(Integer.parseInt(str[i]));
			}
		
			
		} catch (Exception e) {
			//ɾ��ʧ��
			r.setCode(0);
			r.setMessage(e);		
		}
		
		return r;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateEmp/{id}",method=RequestMethod.PUT)
	public Result updateEmp(@PathVariable Integer id,Emp emp,Integer page,Integer rows){
		emp.setId(id);
		Result r  = new Result();
		r.setCode(1);
		
		try {
			service.updateEmp(emp);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		
		return r;
	}
}
