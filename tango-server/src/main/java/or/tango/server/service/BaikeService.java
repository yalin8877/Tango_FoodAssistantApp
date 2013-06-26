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

import java.io.IOException;
import java.net.URLEncoder;

import or.tango.server.model.Baike;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaikeService extends BaseService{
	private static Logger log = Logger.getLogger(BaikeService.class);

	public Object search(String word, String url){
		try {
			Baike wordBaike = new Baike();
			//查找地址
			Document host = Jsoup.connect(url).timeout(10000).get();
			String view = "http://wapbaike.baidu.com"+host.head().html().split("URL=")[1].split("\"")[0];
			System.out.println("view: "+view);
			Document document = Jsoup.connect(view).header("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1").header("Accept", "text ml,application/xhtml+xml").header("Accept-Language", "zh-cn,zh;q=0.5").header("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7") .timeout(10000).get();
			
			document = BaseService.clean(document);
			
//			Element ifo = document.getElementById("main");
//			Elements inf = ifo.select("a[href]");
//			for (Element ins : inf) {
//				String hr = ins.attr("href");
//				String hs = URLEncoder.encode(hr);
//				ins.select("a[href]").attr("href", "http://127.0.0.1:8080/search/api?action=keyWordAction&method=baikeNext&url=http://wapbaike.baidu.com"+hs);
//			}
			
			wordBaike.setFirstPage(document.html());
			//百科名片
			return wordBaike;
		} catch (IOException e) {
			log.error("baidu baike parse:" + e);
			return new Baike();
		}
	}
}
