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

import or.tango.android.Config;
import or.tango.android.R;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SearchActivity extends ActivityGroup {
	private LinearLayout container = null;
	private String keyWord;
	private RadioGroup groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置视图
        setContentView(R.layout.activity_search);
        //获取关键字
        keyWord = getIntent().getStringExtra(Config.ACTIVITY_KEY_WORD);
        Log.i("food", "SearchActivity keyWord="+keyWord);
        
        ((TextView)findViewById(R.id.center_tv)).setText("搜索结果");
        View left = findViewById(R.id.left_btn);
        left.setVisibility(View.VISIBLE);
        left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();//返回
			}
		});
        
        groups = (RadioGroup)findViewById(R.id.btnsGroup);
        groups.setOnCheckedChangeListener(new CheckedChangeListener());
        container = (LinearLayout) findViewById(R.id.containerBody);
        
        groups.check(R.id.baike);//选择一个按钮
    }
    
    
    class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			View rootView=null;
			switch (checkedId) {
			case R.id.baike:
				rootView=getLocalActivityManager().startActivity(
	                    "baike",
	                    new Intent(SearchActivity.this, BaikeActivity.class).putExtra(Config.ACTIVITY_KEY_WORD, keyWord)
	                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
	                    .getDecorView();
				break;
			case R.id.zhidao:
				rootView=getLocalActivityManager().startActivity(
                        "zhidao",
                        new Intent(SearchActivity.this, ZhidaoActivity.class).putExtra(Config.ACTIVITY_KEY_WORD, keyWord)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView();
				break;
			case R.id.xinwen:
				rootView=getLocalActivityManager().startActivity(
                        "xinwen",
                        new Intent(SearchActivity.this, NewsActivity.class).putExtra(Config.ACTIVITY_KEY_WORD, keyWord)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView();
				break;
			default:
				break;
			}
			
			container.removeAllViews();
            container.addView(rootView);
		}
    }


}
