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
package or.tango.android.view;


import or.tango.android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadingView{
	
	private Context context;
	public LoadingView(Context context){
		this.context = context;
	}
	
	public LinearLayout addLoadingLinearLayout(){
		LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
		LinearLayout layout = new LinearLayout(context);
		View view = mInflater.inflate(R.layout.loading, layout,
				true);
		ImageView iView = (ImageView)view.findViewById(R.id.progressimg);
		final AnimationDrawable animDrawable = (AnimationDrawable)context.getResources().getDrawable(R.anim.loading);
		iView.setBackgroundDrawable(animDrawable);
		iView.post(new Runnable(){
	           @Override
	           public void run() {
	               animDrawable.start();
	           }
	        });
		return layout;
	}
	
	
	
}