/**
 * Copyright (C) 2013 wancheng <wancheng.com.cn@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package or.tango.android.activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import or.tango.android.R;
import or.tango.android.util.PictureUpload;

public class MainActivity extends AbstractBaseActivity implements View.OnClickListener{
	private static final int REQUEST_CODE=100;

	@Override
	protected void setLayout() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void findViews() {
		setTitle("配料拍摄");
		findViewById(R.id.btn_photo).setOnClickListener(this);
		findViewById(R.id.btn_photo_system).setOnClickListener(this);
		findViewById(R.id.btn_setting).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_setting){
			startActivity(new Intent(this, SettingActivity.class));
		}
		if(v.getId()==R.id.btn_photo){
			Intent intent = new Intent(this, CameraActivity.class);
			startActivity(intent);
		}
		
		if(v.getId()==R.id.btn_photo_system){
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK){
			if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				showToastMsg("sd卡不可用");
				return;
			}
			
			Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            String photoPath = PictureUpload.savePic(bitmap);
            
            if(bitmap!=null){
            	bitmap.recycle();
            	bitmap=null;
            }
			
            Intent intent = new Intent(this,PicActivity.class);
        	intent.putExtra(CameraActivity.PHOTO_PATH_KEY, photoPath);
        	startActivity(intent);
        	
		}
	}
}
