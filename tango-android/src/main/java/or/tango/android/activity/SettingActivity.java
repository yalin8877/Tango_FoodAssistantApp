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

import android.widget.EditText;

import or.tango.android.Config;
import or.tango.android.R;
import or.tango.android.util.MyPreferences;


public class SettingActivity extends AbstractBaseActivity {
	private EditText host;
	
	@Override
	protected void setLayout() {
		setContentView(R.layout.activity_setting);
	}

	@Override
	protected void findViews() {
		setTitle("设置");
		host = (EditText) findViewById(R.id.hostapi_input);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		host.setText(MyPreferences.getHost(getApplicationContext()));
	}

	@Override
	protected void onPause() {
		super.onPause();
		//保存
		
		MyPreferences.saveHost(getApplicationContext(), host.getText().toString());
		
		Config.HOST=host.getText().toString();
	}
	
}
