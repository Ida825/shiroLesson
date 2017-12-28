package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.FoodMapper;
import cn.et.food.entity.Food;
import cn.et.food.entity.FoodExample;
import cn.et.food.entity.FoodExample.Criteria;
import cn.et.food.service.FoodService;
import cn.et.food.utils.PageTools;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodMapper fm;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.FoodService#queryStudent(java.lang.String)
	 */
	public PageTools queryFood(String foodname,Integer page,Integer rows){
		if(foodname==null){
			foodname="";
		}
		
		FoodExample example = new FoodExample();
		//����һ������
		Criteria c = example.createCriteria();
		//����ж���������Ժ����and...
		c.andFoodnameLike("%"+foodname+"%");
		
		//��ѯ�ܼ�¼��
		int total=(int)fm.countByExample(example);
		
		PageTools ps=new PageTools(page, total, rows);
		//ͨ��RowBounds�Զ����ÿҳ�����
		RowBounds rb = new RowBounds(ps.getStartIndex()-1,rows);
		List<Food> foodList = fm.selectByExampleWithRowbounds(example, rb);
		ps.setRows(foodList);
		return ps;
	}
	
	public int queryFoodCount(FoodExample example){
		
		int total=(int)fm.countByExample(example);
		return total;
	}
	
	
	/**
	 * ��Ӳ�Ʒ
	 */
	public void saveFood(Food food){	
		fm.insert(food);
	}
	
	

	/**
	 * ɾ���Ʒ
	 */
	public void deleteFood(Integer foodid){
		fm.deleteByPrimaryKey(foodid);	
	}
	
	/**
	 * �޸Ĳ�Ʒ
	 */
	public void updateFood(Food food){
		fm.updateByPrimaryKey(food);
	}
	
}
