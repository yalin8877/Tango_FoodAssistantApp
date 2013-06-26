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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import or.tango.server.model.Baike;
import or.tango.server.model.News;
import or.tango.server.model.Zhidao;
import or.tango.server.service.BaikeService;
import or.tango.server.service.BaseService;
import or.tango.server.service.NewsService;
import or.tango.server.service.ZhidaoService;

import org.apache.log4j.Logger;

import com.google.gson.GsonBuilder;

public class SearchController extends BaseController {
	private static Logger log = Logger.getLogger(SearchController.class);
	public static final String NEWS_PARSE = "http://wap.baidu.com/ssid=0/from=0/bd_page_type=1/pu=sz%40224_220/s?tn_1=webmain&tn_4=bdwns&rn=10&pn=0&st_1=111441&st_4=104441&vit=tj&pfr=3-11-bdindex-top-1-search-&ct_4=%E6%90%9C%E6%96%B0%E9%97%BB&word=";
	public static final String ZHIDAO_PARSE = "http://wapiknow.baidu.com/index?rn=10&lm=0&ssid=0&from=0&bd_page_type=1&pu=sz%40224_220%2Cos%40&init=middle&step=1&word=";
	public static final String BAIKE_PARSE = "http://wapbaike.baidu.com/search?uid=EC54A36E502E037BF1C5DD4A61EBF33A&word=";

	public void news(HttpServletRequest request, HttpServletResponse response) {
		String word = request.getParameter("word");
		try {
			word = URLEncoder.encode(word, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BaseService service = new NewsService();
		@SuppressWarnings("unchecked")
		List<News> newsList = (List<News>) service.search(word, NEWS_PARSE + word);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", 1);
		map.put("data", newsList);
		BaseController.responseText(response,new GsonBuilder().create().toJson(map));
	}

	public void zhidao(HttpServletRequest request, HttpServletResponse response) {
		String word = request.getParameter("word");
		try {
			word = URLEncoder.encode(word, "utf8");
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		BaseService service = new ZhidaoService();
		@SuppressWarnings("unchecked")
		List<Zhidao> wordZhidaoList = (List<Zhidao>) service.search(word,ZHIDAO_PARSE+ word);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", 1);
		map.put("data", wordZhidaoList);
		BaseController.responseText(response,new GsonBuilder().create().toJson(map));
	}

	public void baike(HttpServletRequest request, HttpServletResponse response) {
		String word = request.getParameter("word");
		try {
			word = URLEncoder.encode(word, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BaseService service = new BaikeService();
		Baike wordBaike = (Baike) service.search(word, BAIKE_PARSE
				+ word + "&ssid=&st=1&bd_page_type=1&bk_fr=srch");
		List<Baike> rs = new ArrayList<Baike>();
		rs.add(wordBaike);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", 1);
		map.put("data", rs);
		BaseController.responseText(response,new GsonBuilder().create().toJson(map));
	}
}
