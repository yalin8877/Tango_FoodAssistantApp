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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import or.tango.android.Config;
import or.tango.android.R;
import or.tango.android.pojo.SearchData;
import or.tango.android.pojo.SearchData.Item;
import or.tango.android.util.GsonUtils;
import or.tango.android.util.HttpUtil;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**新闻
 *
 */
public class NewsActivity extends AbstractBaseActivity {
	private String keyWord;
	private SearchData searchData;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	
	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			if(msg.what==200){
				hideProgress();
				
				String data = (String)msg.obj;
				Log.i("food", "------------>"+data);
				
				try{
					searchData = GsonUtils.getGson().fromJson(data, SearchData.class);
					Log.i("food", "------------>searchData.data.size()="+searchData.data.size());
					if(searchData!=null && searchData.data!=null){
						ArrayList<Item> items = searchData.data;
						for(int i=0;i<items.size();i++){
							adapter.add(items.get(i).title);
						}
					}
				}catch (Exception e) {
					showToastMsg("没有搜索到"+keyWord+"的新闻");
				}
			}
		};
	};

	@Override
	protected void setLayout() {
		setContentView(R.layout.activity_news);
	}

	

	@Override
	protected void findViews() {
		keyWord = getIntent().getStringExtra(Config.ACTIVITY_KEY_WORD);
		
		listView = (ListView)findViewById(R.id.contentListView);
		adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_expandable_list_item_1
                );
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ItemClickListener());
		
		showProgress("加载中");
		
		getData();
	}
	
	public class ItemClickListener implements AdapterView.OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			Item item = searchData.data.get(position);
			
			Intent intent = new Intent(NewsActivity.this, WebViewActivity.class);
			intent.putExtra(Config.ACTIVITY_KEY_URL, item.url);
			startActivity(intent);
		}
	}

	private void getData(){
		new Thread(){
			public void run() {
				String httpUrl = Config.HOST+Config.NEWS;
				Map<String, Object> httpParams = new HashMap<String, Object>();
				httpParams.put("word", keyWord);
				Log.i("food", "------------>keyWord="+keyWord);
				Map<String, String> headerParams = new HashMap<String, String>();
				String str = HttpUtil.sendRequest(httpUrl, headerParams, httpParams, "post");
				
				Message msg = handler.obtainMessage();
				msg.what=200;
				msg.obj=str;
				handler.sendMessage(msg);
			};
		}.start();
		
	}
}
