package com.ilucky.aplay.core.activity.rank;

import java.io.File;
import java.util.List;

import org.apache.http.util.TextUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilucky.aplay.R;
import com.ilucky.aplay.core.activity.login.LoginActivity;
import com.ilucky.aplay.core.application.AplayApplication;
import com.ilucky.aplay.core.consts.Consts;
import com.ilucky.aplay.core.model.Rank;
import com.ilucky.aplay.util.android.FileUtil;
import com.ilucky.aplay.util.android.LogUtil;
import com.ilucky.aplay.util.android.SharedPreferenceUtil;
import com.ilucky.aplay.util.android.ToastUtil;

/**
 * @author IluckySi
 * @since 20150724
 */
public class RankAdapter extends ArrayAdapter<Rank> {

	private static final String TAG = "RankAdapter";
	private int resourceId;
	
	public RankAdapter(Context context, int textViewResourceId, List<Rank> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Rank rank = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.photoImageView = (ImageView) view.findViewById(R.id.fragment_rank_itme_iv_photo);
			viewHolder.nameTextView = (TextView) view.findViewById(R.id.fragment_rank_itme_tv_name);
			viewHolder. totalScoreTextView = (TextView) view.findViewById(R.id.fragment_rank_itme_tv_total_score);
			viewHolder.sexImageView = (ImageView) view.findViewById(R.id.fragment_rank_itme_iv_sex);
			viewHolder.pkButton = (Button) view.findViewById(R.id.fragment_rank_itme_bn_pk);
			viewHolder.pkButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					invitePk(rank);
				}
			});
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		Bitmap bitmap = null;
		try {
			if(rank.getPhoto() == null) {
				bitmap = BitmapFactory.decodeStream(FileUtil.getAssestInputStream("photo" + File.separator + Consts.DEFAULT_PHOTO));
			}  else {
				bitmap = BitmapFactory.decodeFile(rank.getPhoto());
			}
		} catch (Exception e) {
			LogUtil.e(TAG, e.toString());
		}
		viewHolder.photoImageView.setImageBitmap(bitmap);
		viewHolder.nameTextView.setText(rank.getName());
		viewHolder.totalScoreTextView.setText(rank.getTotalScore());
		viewHolder.sexImageView.setImageResource(rank.getSex());
		return view;
	}
	
	class ViewHolder {
		private ImageView photoImageView;
		private TextView nameTextView;
		private TextView totalScoreTextView;
		private ImageView sexImageView;
		private Button pkButton;
	}
	
	private void invitePk(Rank rank) {
		Context context = AplayApplication.getContext();
		String inviter = SharedPreferenceUtil.read("user", "id");
		if(TextUtils.isEmpty(inviter)) {
			Intent loginIntent = new Intent(context, LoginActivity.class);
			loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(loginIntent);
		} else {
			if(inviter.equals(rank.getUser())) {
				ToastUtil.toast(Consts.RANK_ADAPTER_CANNOT_PK_SELF);
			} else {
				Intent invitePkIntent = new Intent(context, InvitePkActivity.class);
				invitePkIntent.putExtra("from", inviter);
				invitePkIntent.putExtra("to", rank.getUser());
				invitePkIntent.putExtra("toName", rank.getName());
				invitePkIntent.putExtra("toPhoto", rank.getPhoto());
				invitePkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(invitePkIntent);
			}
		}
	}
}
