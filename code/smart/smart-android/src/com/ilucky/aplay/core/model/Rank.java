package com.ilucky.aplay.core.model;

/**
 * @author IluckySi
 * @since 20150724
 */
public class Rank {

	private String id;
	private String user;
	private String photo;
	private String name;
	private String totalScore;
	private int sex;
	private String goldMedal;
	private String silverMedal;
	private String bronzeMedal;
	private String length;
	
	public Rank(String id, String user, String photo, String name, String totalScore, int sex, String goldMedal, String silverMedal, String bronzeMedal, String length) {
		super();
		this.id = id;
		this.user = user;
		this.photo = photo;
		this.name = name;
		this.totalScore = totalScore;
		this.sex = sex;
		this.goldMedal = goldMedal;
		this.silverMedal = silverMedal;
		this.bronzeMedal = bronzeMedal;
		this.length = length;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getGoldMedal() {
		return goldMedal;
	}
	public void setGoldMedal(String goldMedal) {
		this.goldMedal = goldMedal;
	}
	public String getSilverMedal() {
		return silverMedal;
	}
	public void setSilverMedal(String silverMedal) {
		this.silverMedal = silverMedal;
	}
	public String getBronzeMedal() {
		return bronzeMedal;
	}
	public void setBronzeMedal(String bronzeMedal) {
		this.bronzeMedal = bronzeMedal;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
}
