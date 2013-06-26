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
package or.tango.server.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileService {
	public static String pickupText(File file) {
		String os = System.getProperty("os.name");
		StringBuilder result = new StringBuilder();

		//if ("Linux".equals(os)) {
			StringBuilder command = new StringBuilder("tesseract ");
			command.append(file.getAbsolutePath()).append(" ")
					.append(file.getAbsolutePath());
			command.append(" -l chi_sim");
			System.out.println("command:"+command);
			BufferedReader reader;
			try {
				Process process = Runtime.getRuntime().exec(command.toString());
				
				InputStream stderr = process.getErrorStream();
				InputStreamReader isr = new InputStreamReader(stderr);
				BufferedReader br = new BufferedReader(isr);
				String error = null;
				while((error = br.readLine()) != null){
					System.out.println(error);
				}
				br.close();
				System.out.println("===============");
				File text = new File(file.getAbsolutePath() + ".txt");
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(text),"UTF-8"));

				String temp;
				while ((temp = reader.readLine()) != null)
					result.append(temp);
				System.out.println("result:"+result);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//		} else {
//			System.out.println("file:"+file.getAbsolutePath());
//			List<String> list = new ArrayList();
//			list.add("baidu");
//			return new GsonBuilder().create().toJson(list);
//		}
		return result.toString();
	}
}
