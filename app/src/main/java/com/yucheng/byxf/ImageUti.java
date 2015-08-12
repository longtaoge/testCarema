package com.yucheng.byxf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;

public class ImageUti {

	
	
	/**
	 * 获取图片信息
	 * 
	 * @param path
	 * @return
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;

	}

	
	
	
	
	
	
	
	/**
	  * 图片旋转
	  * 
	  * @param angle
	  * @param bitmap
	  * @return
	  */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
	  // 旋转图片 动作
	  Matrix matrix = new Matrix();
	  matrix.postRotate(angle);
	  System.out.println("angle=" + angle);
	  // 创建新的图片
	  Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
	    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	  return resizedBitmap;
	 }
	
	
	
	/**
     * 裁剪图片方法实现
     * @param uri
     */
	public void startPhotoZoom(Activity activity,Uri uri) {
    
        /*
         * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
         * yourself_sdk_path/docs/reference/android/content/Intent.html
         * 直接在里面Ctrl+F搜：CROP 
         *
         */
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 3);
    }
	
	
	private static final int DEFAULT_requireD_SIZE = 70;
	public static Bitmap decodeFile(File f, int size) {
		try {
			BitmapFactory.Options option = new BitmapFactory.Options();
			option.inJustDecodeBounds = true;
			FileInputStream stream1 = new FileInputStream(f);
			BitmapFactory.decodeStream(stream1, null, option);
			stream1.close();
			final int requireD_SIZE = size > 0 ? size : DEFAULT_requireD_SIZE;
			int width_tmp = option.outWidth, height_tmp = option.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < requireD_SIZE
						|| height_tmp / 2 < requireD_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			if (scale >= 2) {
				scale /= 2;
			}
			BitmapFactory.Options option2 = new BitmapFactory.Options();
			option2.inSampleSize = scale;
			FileInputStream stream2 = new FileInputStream(f);
			Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, option2);
			stream2.close();
			return bitmap;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
