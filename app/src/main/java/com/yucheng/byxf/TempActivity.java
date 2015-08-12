package com.yucheng.byxf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.yucheng.byxf.mini.ui.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TempActivity extends Activity implements OnClickListener {

	protected static final int GET_IMAGE_VIA_CAMERA = 0;

	// 身份证返面
	private ImageView imageView2;

	File destination = new File(getPath("addd", "test"));
	/**
	 * 取消贷款
	 */

	private Button next_button;

	final String localTempImgDir = "abc";

	final String localTempImgFileName = "test";

	private int REQUEST_IMAGE = 0;

	private String imagePath;

	protected Uri mOutPutFileUri ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.aaaa_rapidly_id_card_activity);
		destination = new File(getPath("addd", "test"));
		
		
		mOutPutFileUri= Uri.parse(getPath("addd", "test"));
		initView();

	}

	/**
	 * 获取图片路径缓存
	 */

	private void initView() {

		imageView2 = (ImageView) findViewById(R.id.imageView2);

		next_button = (Button) findViewById(R.id.next_button);

		next_button.setOnClickListener(this);

		imageView2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 文件夹aaaa
				String path = Environment.getExternalStorageDirectory()
						.toString() + "/aaaa";
				File path1 = new File(path);
				if (!path1.exists()) {
					path1.mkdirs();
				}
				File file = new File(path1, System.currentTimeMillis() + ".jpg");
				mOutPutFileUri  = Uri.fromFile(file);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
				startActivityForResult(intent, 1);

			}

		});

	}

	// 在onActivityResult中这样折腾：

	@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
		 
		 super.onActivityResult(requestCode, resultCode, data);
		  if(requestCode == 1){
		   Uri imageUri = null;
		   if(data != null){
		    if(data.hasExtra("data")){
		     Bitmap thunbnail = data.getParcelableExtra("data");
		     //处理缩略图
		    }
		   }else{
		    //处理mOutPutFileUri中的完整图像
		   }
		 
		 
	 }
	}
	@Override
	public void onClick(View v) {

	}

	public static String getPath(String appCode, String flag) {
		File DatalDir = Environment.getExternalStorageDirectory();
		File myDir = new File(DatalDir, "/DCIM/");
		myDir.mkdirs();
		String mDirectoryname = DatalDir.toString() + "/DCIM/";
		String fileName = appCode + flag;
		// LogManager.i(ImageDispose.class, fileName);
		return mDirectoryname + fileName + ".jpg";
	}

}
