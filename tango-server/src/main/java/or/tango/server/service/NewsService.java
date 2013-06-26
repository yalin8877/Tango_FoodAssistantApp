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

import or.tango.server.model.News;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsService extends BaseService{
	private static Logger log = Logger.getLogger(NewsService.class);

	public List<News> search(String word, String url){
		try {
			List<News> newsList = new ArrayList<News>();
			Document document = Jsoup.connect(url).timeout(10000).get();
			Elements es = document.getElementsByClass("mb");
			for (Element element : es) {
				News news = new News();
				Element ea = element.select("a").first();
				String title = ea.text();
				String href = ea.attr("href").replaceFirst("./", "http://wap.baidu.com/");
				news.setTitle(title);
				news.setUrl(href);
				news.setCreateDate(System.currentTimeMillis());
				newsList.add(news);
				System.out.println("baidu zhidao -->title:"+title+",href:"+href);
			}
			return newsList;
		} catch (IOException e) {
			log.error("baidu baike parse:" + e);
			return  new ArrayList<News>();
		}
	}
}
