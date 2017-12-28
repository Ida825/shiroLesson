package cn.et.food.service;

import cn.et.food.entity.Food;
import cn.et.food.utils.PageTools;

public interface FoodService {

	public abstract PageTools queryFood(String foodname,Integer page,Integer rows);
	
	
	public void saveFood(Food food);
	
	public void deleteFood(Integer foodid);
	
	public void updateFood(Food food);
}