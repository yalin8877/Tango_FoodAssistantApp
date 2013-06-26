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
import java.util.ArrayList;
import java.util.List;

import or.tango.server.model.Zhidao;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZhidaoService extends BaseService{
	private static Logger log = Logger.getLogger(ZhidaoService.class);

	public List<Zhidao> search(String word, String url){
		try {
			List<Zhidao> zhidaolist = new ArrayList<Zhidao>();
			Document document = Jsoup.connect(url).timeout(10000).get();
			System.out.println(document.html());
			Element es = document.getElementsByClass("slist").first();
			Elements eas = es.getElementsByClass("a2");
			for (Element element : eas) {
				Zhidao wordZhidao = new Zhidao();
				Element pe = element.select("a").first();
				String title = pe.text();
				String href = "http://wapiknow.baidu.com"+pe.attr("href");
				wordZhidao.setTitle(title);
				wordZhidao.setUrl(href);
				wordZhidao.setCreateDate(System.currentTimeMillis());
				zhidaolist.add(wordZhidao);
				System.out.println("baidu zhidao -->title:"+title+",href:"+href);
			}
			return zhidaolist;
			//百科名片
		} catch (IOException e) {
			log.error("baidu baike parse:" + e);
			return new ArrayList<Zhidao>();
		}
	}
	
}
