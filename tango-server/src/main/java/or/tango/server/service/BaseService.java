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

import org.jsoup.nodes.Document;

public abstract class BaseService {

	
	
	/**
	 * 解析html
	 * @param document
	 */
	public abstract Object search(String word, String url);
	
	public static Document clean(Document document){
		document.getElementsByClass("crumbs").remove();
		document.getElementsByClass("promotion").remove();
		document.getElementsByClass("rec").remove();
		document.getElementsByClass("promotion down").remove();
		document.getElementsByClass("m-ft").remove();
		document.getElementsByClass("rec-con").remove();
		document.getElementsByClass("footer").remove();
		return document;
	}

}
