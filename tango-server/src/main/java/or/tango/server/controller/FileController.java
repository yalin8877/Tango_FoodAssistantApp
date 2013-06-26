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

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import or.tango.server.service.FileService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileController extends BaseController {

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4 * 1024);
		
		String tmpdir = System.getProperty("java.io.tmpdir"); 
		factory.setRepository(new File(tmpdir));
		ServletFileUpload upload = new ServletFileUpload(factory);
		System.out.println("tmpdir:"+tmpdir);
		String result = null;
		try {
			FileItem item = upload.parseRequest(request).get(0);
			File file = new File(System.currentTimeMillis()+item.getName());
			item.write(file);			
			result = FileService.pickupText(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseController.resonseJson(response,result);
	}
	
	public void test (HttpServletRequest request, HttpServletResponse response){
		String html = "<html><head><title>upload test</title></head>" +
				"<body><form action='api?action=File&method=upload' method='post' enctype='multipart/form-data'>" +
				"<input type='file' name='p'/><input type='submit'/></form>" +
				"</body></html>";
		BaseController.resonseHtml(response,html);
	}
}
