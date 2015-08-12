package com.yucheng.byxf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yucheng.byxf.mini.ui.R;

public class RapidlyIDCardActivity extends BaseActivity implements OnClickListener {
	// 状态码
	private int RET_CODE = 0;
	// 识别码
	private String applyCode;

	private RelativeLayout mTitleLayout;
	private TextView title;

	// 身份证正面
	private ImageView imageView1;
	// 身份证返面
	private ImageView imageView2;

	private String path1;

	private String path2;

	/**
	 * 取消贷款
	 */

	private Button next_button;

	private Button back;

	// 信息有误
	private final int INFO_ERROR = 0;

	private String TAG = "RapidlyIDCardActivity";

	private final int REAPPLYCODETASK = 10001;
	private final int DIALOGEDISMISS = 10002;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);

			switch (msg.what) {
			case REAPPLYCODETASK:

				break;

			case DIALOGEDISMISS:

				finish();

				break;
			default:
				break;
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.aaaa_rapidly_id_card_activity);
		initView();

	}

	/**
	 * 获取图片路径缓存
	 */

	private void initView() {

		// 初始化 布局
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);

		next_button = (Button) findViewById(R.id.next_button);

		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);

		next_button.setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (RESULT_OK == resultCode) {

		
		
			ImageView imgView = null;

			Bundle extras = data.getExtras();
			Bitmap myBitmap = (Bitmap) extras.get("data");
		

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			if (myBitmap != null) {

				switch (requestCode) {
				case 1:
					

					imgView = imageView1;
				

					break;
				case 2:
					

					imgView = imageView2;
				

					break;

				default:
					break;
				}

				
			
				imgView.setImageBitmap(myBitmap);
				
				
				
			}

		} else {
			// TODO
			Toast.makeText(RapidlyIDCardActivity.this, "拍照过程中遇到问题，请重新拍照！",
					Toast.LENGTH_LONG).show();
			// result = new RegexResult(false, "拍照过程中遇到部题，请重新拍照！");
			// showDialog(INFO_ERROR);

		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.imageView1:

			Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// intent1.putExtra(MediaStore.EXTRA_OUTPUT,
			// Uri.fromFile(new File(path1)));
			startActivityForResult(intent1, 1);

			break;

		case R.id.imageView2:

			Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// intent2.putExtra(MediaStore.EXTRA_OUTPUT,
			// Uri.fromFile(new File(path2)));
			startActivityForResult(intent2, 2);
			break;

		case R.id.next_button:
			Intent intent = new Intent();

			// 清空缓存

			// intent.setClass(this, RapidlyLoanInfoConfrim.class);
			// TODO startActivity(intent);

			// startActivity(intent);
			// setPostInfo();
			// TODO OCR 验证

			;

			// finish();
			break;

		default:
			break;
		}

	}

	@Override
	protected void onResume() {

		super.onResume();

	}

	/**********************************************/

	/**
	 * 获取人脸识别 验证码
	 * 
	 * @author longtaoge
	 * 
	 */

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	public void set() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 下面这句指定调用相机拍照后的照片存储的路径
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
				Environment.getExternalStorageDirectory(), "test")));
		// SQBConstants.tempImgFile文件名public static final String tempImgFile =
		// "/avatar.png";// intent.putExtra(MediaStore.Images.Media.ORIENTATION,
		// 0);// intent.putExtra("return-data", true);
		startActivityForResult(intent, 9);// SQBConstants.AVATAR_CAMERA为常量值，在返回Activity时使用，可自行定义

	}



	

}
