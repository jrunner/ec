package com.ilucky.aplay.core.activity.common;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.SharedPreferenceUtil;
import com.ilucky.aplay.util.http.HttpUtil;

/**
 * @author IluckySi
 * @sicne 20150820
 */
public class Common {

	private static final String TAG = "LOGIN";
	
	public static void renameUserPhoto(Map<Object, Object> responseMap, File photoFile) {
		try {
			if(photoFile != null) {
				@SuppressWarnings("unchecked")
				Map<Object, Object> responseMapUser = (Map<Object, Object>)responseMap.get("user");
				String id =responseMapUser.get("id").toString();
				File photoDir = FileUtil.getOtherDir("photo");
				File newPhotoFile = new File(photoDir.getPath() + File.separator + id + Consts.PNG);
				if(!newPhotoFile.exists()) {
					try {
						newPhotoFile.createNewFile();
					} catch (IOException e) {
						LogUtil.e(TAG, e.toString());
					}
				}
				photoFile.renameTo(newPhotoFile);
				LogUtil.d(TAG, "重命名用户头像成功");
			}
		} catch (Exception e) {
			LogUtil.e(TAG, "重命名用户头像失败:"+e);
		}
	}
	
	public static boolean saveUserData(Map<Object, Object> responseMap) {
		try {
			@SuppressWarnings("unchecked")
			Map<Object, Object> responseMapUser = (Map<Object, Object>)responseMap.get("user");
			SharedPreferenceUtil.write(Consts.USER, "id", responseMapUser.get("id").toString());
			SharedPreferenceUtil.write(Consts.USER, "name",  responseMapUser.get("name").toString());
			SharedPreferenceUtil.write(Consts.USER, "password",  responseMapUser.get("password").toString());
			SharedPreferenceUtil.write(Consts.USER, "phone", responseMapUser.get("phone").toString());
			SharedPreferenceUtil.write(Consts.USER, "sex", responseMapUser.get("sex").toString());
			SharedPreferenceUtil.write(Consts.USER,"birthday", responseMapUser.get("birthday").toString());
			SharedPreferenceUtil.write(Consts.USER, "totalScore", responseMapUser.get("totalScore").toString());
			SharedPreferenceUtil.write(Consts.USER,"avalScore", responseMapUser.get("avalScore").toString());
			SharedPreferenceUtil.write(Consts.USER,"registerTime", responseMapUser.get("registerTime").toString());
			SharedPreferenceUtil.write(Consts.USER,"lastLoginTime", responseMapUser.get("lastLoginTime").toString());
			SharedPreferenceUtil.write(Consts.USER, "length", responseMapUser.get("length").toString());
			File photoDir = FileUtil.getOtherDir("photo");
			String photoName = responseMapUser.get("id").toString() + Consts.PNG;
			File photoFile = new File(photoDir.getPath() + File.separator + photoName);
			long length = Long.parseLong(responseMapUser.get("length").toString());
			if((!photoFile.exists() && length > 0) ||  photoFile.length() != length ) {
				String photoUrl = Consts.WEB_ADDRESS + Consts.USER_PHOTO + responseMapUser.get("id").toString() + ".png";
				if(HttpUtil.download(photoUrl, photoFile.getPath())) {
					LogUtil.d(TAG, "下载用户头像成功");
				} else {
					LogUtil.e(TAG, "下载用户头像失败");
				}
			} 
			LogUtil.d(TAG, "用户信息保存成功");
			return true;
		} catch (Exception e) {
			LogUtil.e(TAG, "用户信息保存失败:"+e);
			return false;
		}
	}
	
	public static Map<Object, Object> getUserData() {
		Map<Object, Object> user = new HashMap<Object, Object>();
		try {
			user.put("id", SharedPreferenceUtil.read(Consts.USER, "id"));
			user.put("name", SharedPreferenceUtil.read(Consts.USER, "name"));
			user.put("sex", SharedPreferenceUtil.read(Consts.USER, "sex"));
			user.put("birthday", SharedPreferenceUtil.read(Consts.USER, "birthday"));
			user.put("totalScore", SharedPreferenceUtil.read(Consts.USER, "totalScore"));
			user.put("goldMedal", SharedPreferenceUtil.read(Consts.USER, "goldMedal"));
			user.put("silverMedal", SharedPreferenceUtil.read(Consts.USER, "silverMedal"));
			user.put("bronzeMedal", SharedPreferenceUtil.read(Consts.USER, "bronzeMedal"));
			user.put("length", SharedPreferenceUtil.read(Consts.USER, "length"));
			LogUtil.d(TAG, "获取用户信息成功");
		} catch (Exception e) {
			LogUtil.d(TAG, "获取用户信息失败:"+e);
		}
		return user;
	}
}
