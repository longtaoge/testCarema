package com.yucheng.byxf;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.yucheng.byxf.mini.ui.R;

public class testCarema extends Activity {
	/** Called when the activity is first created. */
	private ImageView imageView;
	private OnClickListener imgViewListener;
	private Bitmap myBitmap;

	// private byte[] mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView = (ImageView) findViewById(R.id.imageView);
		imgViewListener = new OnClickListener() {
			public void onClick(View v) {
				final CharSequence[] items = { "相册", "拍照" };
				
				AlertDialog dlg = new AlertDialog.Builder(testCarema.this)
						.setTitle("选择图片")
						.setItems(items, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// 这里item是根据选择的方式，
								// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
								if (item == 1) {
									Intent getImageByCamera = new Intent(
											"android.media.action.IMAGE_CAPTURE");
									startActivityForResult(getImageByCamera, 1);
								} else {
									Intent getImage = new Intent(
											Intent.ACTION_GET_CONTENT);
									getImage.addCategory(Intent.CATEGORY_OPENABLE);
									getImage.setType("image/jpeg");
									startActivityForResult(getImage, 0);
								}
							}
						}).create();
				dlg.show();
			}
		};
		// 给imageView控件绑定点点击监听器
		imageView.setOnClickListener(imgViewListener);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		ContentResolver resolver = getContentResolver();
		/**
		 * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
		 * 所以为了区别到底选择了那个方式获取图片要进行判断，
		 * 这里的requestCode跟startActivityForResult里面第二个参数对应
		 */

		if (requestCode == 1) {
			try {
				super.onActivityResult(requestCode, resultCode, data);
				Bundle extras = data.getExtras();
				myBitmap = (Bitmap) extras.get("data");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			} catch (Exception e) {

				e.printStackTrace();
			}
			// 把得到的图片绑定在控件上显示

			imageView.setImageBitmap(myBitmap);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

}