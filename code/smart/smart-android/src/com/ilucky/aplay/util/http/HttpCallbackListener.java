package com.ilucky.aplay.util.http;

/**
 * @author IluckySi
 * @since 20150724
 */
public interface HttpCallbackListener {

	public void onSuccess(String response);
	public void onFailure(String response);
}
