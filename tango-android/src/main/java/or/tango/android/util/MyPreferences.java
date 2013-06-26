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
package or.tango.android.util;

import or.tango.android.Config;

import android.content.Context;

/**
 * ip缓存
 * 
 * 
 */
public class MyPreferences {

	private static final String MY_SHAREDPREFERENCES_NAME = "myPre";

	private static final String KEY_HOST = "host";

	public static String getHost(Context context) {
		if (context == null)
			return Config.HOST;

		String host = context.getSharedPreferences(MY_SHAREDPREFERENCES_NAME,
				Context.MODE_WORLD_READABLE).getString(KEY_HOST, "");

		if ("".equals(host))
			return Config.HOST;
		return host;
	}

	public static boolean saveHost(Context context, String host) {
		if (context == null || host == null || "".equals(host))
			return false;

		return context
				.getSharedPreferences(MY_SHAREDPREFERENCES_NAME,
						Context.MODE_WORLD_WRITEABLE).edit()
				.putString(KEY_HOST, host).commit();
	}

}
