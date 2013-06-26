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
package or.tango.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class BaseController {
	public static void responseText(HttpServletResponse response,String json) {
		PrintWriter out = null;		
		response.setContentType("application/json;charset=UTF-8");
		
		try {
			out = response.getWriter();
			out.println(json);
		} catch (IOException e) {
			// todo
		}finally {
			if (out != null) out.close();
		}
		
	}
	public static void resonseJson(HttpServletResponse response,String content){
		response.setContentType("application/json;charset=UTF-8");
		response(response,content);
	}
	public static void resonseHtml(HttpServletResponse response,String content){
		response(response,content);
	}
	public static void response(HttpServletResponse response,String content){
		try {
			response.getWriter().println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
