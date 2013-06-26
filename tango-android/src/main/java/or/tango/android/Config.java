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
package or.tango.android;


public class Config {
	
	public static boolean DEBUG_LOG=true;//调试日志输出开关
	public static String INSIGHT_FOLDER="/insightech";//应用保存目录
	public static final String ACTIVITY_KEY_WORD="keywords";//ACITIVITY传递的keyword
	public static final String ACTIVITY_KEY_URL="url";//ACITIVITY传递的keyword
		
	/******************************接口服务器路径*****************************/
	public static  String HOST = "http://192.168.1.100:8080/tango/api?"; //用于本地未设置ip时的默认ip
	
	public static final String BAIKE = "action=Search&method=baike";
	public static final String NEWS ="action=Search&method=news";
	public static final String ZHIDAO = "action=Search&method=zhidao";
	public static final String FILE = "action=File&method=upload";
}
