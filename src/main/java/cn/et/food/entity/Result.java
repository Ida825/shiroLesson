package cn.et.food.entity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Result {
	/**
	 * ����code:0 ���ʧ�ܣ�1���ɹ�
	 */
	private int code;
	
	/**
	 * ��ʧ�ܵ���Ϣ����message
	 */
	private String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {	
		 if(message!=null && message.length()>1000){
			 return message.substring(0, 1000);			 
		 }
		return message;
	}
	public void setMessage(String message) {
		this.message=message;
	}
	
	
	public void setMessage(Exception msg) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		msg.printStackTrace(new PrintStream(bs));
		this.message=new String(bs.toByteArray());
	}
	
	
}
