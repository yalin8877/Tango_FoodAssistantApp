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
package or.tango.server.dispatcher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DefaultDispatcher implements Filter{
	private static Logger log = Logger.getLogger(DefaultDispatcher.class);

	
	public void doFilter(ServletRequest re, ServletResponse rs,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) re;
		HttpServletResponse response = (HttpServletResponse) rs;
		
		String action = request.getParameter("action");	
		String method = request.getParameter("method");
		System.out.println("user access action:"+action+" method:"+method);
		try {
			Class<?> c = Class.forName("or.tango.server.controller."+action+"Controller");
			Object o = c.newInstance();	
			
			Class<?>[] args = new Class[2];
			args[0] = HttpServletRequest.class;
			args[1] = HttpServletResponse.class;
			
			Method m = c.getDeclaredMethod(method.toLowerCase(),args);
			m.invoke(o,request,response);
		} catch (NoSuchMethodException  e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch(ClassNotFoundException e){
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (InvocationTargetException e){
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (InstantiationException e){
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IllegalAccessException e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
