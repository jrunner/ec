package com.ilucky.aplay.util.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.ilucky.aplay.util.android.LogUtil;

/**
 * @author IluckySi
 * @since 20150701
 */
public class HttpUtil {

	private static String TAG = "HttpUtil";

	public static void post(final String url, final Map<Object, Object> message, final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				try {
					HttpPost post = new HttpPost(url);
					HttpParams httpParams = new BasicHttpParams(); 
		            HttpConnectionParams.setConnectionTimeout(httpParams, 5000); 
		            HttpConnectionParams.setSoTimeout(httpParams, 6000); 
		            post.setParams(httpParams);
					List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
					for (Entry<Object, Object> entry : message.entrySet()) {
						Object value = entry.getValue();
						if(value == null) {
							continue;
						}
						params.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
					}
					post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
					HttpResponse response = httpClient.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						String content = EntityUtils.toString(response.getEntity());
						listener.onSuccess(content);
					} else {
						String error = "请求url="+url+"返回错误状态码:"+response.getStatusLine().getStatusCode();
						listener.onFailure(error);
					}
				} catch (Exception e) {
					LogUtil.e(TAG, "发送请求发生异常:"+e);
					listener.onFailure(e.toString());
				}
			}
		}).start();
	}
	
	public static void post2(final String url, final Map<Object, Object> message, final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				try {
					 HttpPost post = new HttpPost(url); 
					 HttpParams httpParams = new BasicHttpParams(); 
		             HttpConnectionParams.setConnectionTimeout(httpParams, 5000); 
		             HttpConnectionParams.setSoTimeout(httpParams, 6000); 
		             post.setParams(httpParams);
					 String boundary = System.currentTimeMillis()+"";
					 post.setHeader("Content-type", "multipart/form-data; boundary="+boundary);
					 MultipartEntityBuilder meb = MultipartEntityBuilder.create();
					 meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
					 meb.setBoundary(boundary);
				 	for (Entry<Object, Object> entry : message.entrySet()) {
				 		Object value = entry.getValue();
				 		if(value == null) {
				 			continue;
				 		}
				 		if(value instanceof File) {
				 			FileBody fileBody = new FileBody((File)value);
				 			meb.addPart(entry.getKey().toString(), fileBody); 
				 		} else {
				 			ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
							StringBody stringBody = new StringBody(entry.getValue().toString(), contentType);
					 		meb.addPart(entry.getKey().toString(), stringBody); 
				 		}
				 	}
				 	HttpEntity entity = meb.build();
				 	post.setEntity(entity);
				 	HttpResponse response = httpClient.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						String content = EntityUtils.toString(response.getEntity());
						listener.onSuccess(content);
					} else {
						String error = "请求url="+url+"返回错误状态码:"+response.getStatusLine().getStatusCode();
						listener.onFailure(error);
					}
				} catch (Exception e) {
					LogUtil.e(TAG, "发送请求发生异常:"+e);
					listener.onFailure(e.toString());
				}
			}
		}).start();
	}
	
	public static boolean download(String url, String local) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		InputStream is = null;
		BufferedInputStream bis = null;
		File localFile = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			HttpResponse response = client.execute(get);
			//TODO:需要优化这里的长度要和服务器返回的长度一致,才进行下载,并且下载完后,还要进行判断.
			 long fileLength = response.getEntity().getContentLength();
			 if(fileLength <= 2048) {
				 return false;
			 }
			is = response.getEntity().getContent();
			bis = new BufferedInputStream(is);
			localFile = new File(local);
			fos = new FileOutputStream(localFile);
			bos = new BufferedOutputStream(fos);
			byte[] byteArray = new byte[1024];
			int length = 0;
			while ((length = bis.read(byteArray)) != -1) {
				bos.write(byteArray, 0, length);
				bos.flush();
			}
			return true;
		} catch (Exception e) {
			LogUtil.e(TAG, "下载文件发生异常:"+e);
			return false;
		} finally {
			try {
				if (bos != null) {
					bos.close();
					bos = null;
				}
				if (fos != null) {
					fos.close();
					fos = null;
				}
				if (bis != null) {
					bis.close();
					bis = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (Exception e) {
				LogUtil.e(TAG, "关闭文件流发生异常:"+e);
			}
		}
	}
}
