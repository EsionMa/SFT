/**
 * 2017-08-07 10:58:04
 */
package com.wangzhixuan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author Esion
 *
 */
public class HttpUtil {


	/** Token */
	public static String accseeToken = "";
	/** 请求方式-POST */
	public static final String REQUEST_METHOD_POST = "POST";
	/** 请求方式-GET */
	public static final String REQUEST_METHOD_GET = "GET";


	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param
	 * @param requestMethod
	 * @return
	 */
	public static Map<String,Object> sendRequest(String url, Map<String, Object> parms, String requestMethod) {
		String result = "";
		// 声明输入流
		BufferedReader in = null;
		// 声明输出流对象
		PrintWriter printWriter = null;
		try {
			String urlParam = toUrlParam(parms, requestMethod);
			// url请求参数
			String requestUrl = url + urlParam;
			// json请求参数
			String jsonPram = toJsonParam(parms, requestMethod);
			// 生成url
			URL realurl = new URL(requestUrl);
			// 建立链接对象
			URLConnection conn = realurl.openConnection();
			// 设置请求头
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("__token", accseeToken);
			if (REQUEST_METHOD_GET.equals(requestMethod)) {
				// GET请求建立实际的链接
				conn.connect();
			} else if (REQUEST_METHOD_POST.equals(requestMethod)) {
				// 发送POST请求必须设置如下两行
				conn.setDoOutput(true);
				conn.setDoInput(true);
				// 获得对应链接的输出流
				printWriter = new PrintWriter(conn.getOutputStream());
				printWriter.println(jsonPram);
				// 刷新缓冲
				printWriter.flush();
			}

			// 获取响应头
			Map<String, List<String>> httpResponseHeader = conn.getHeaderFields();
			// 打印响应头
			Iterator<Map.Entry<String, List<String>>> iterator = httpResponseHeader.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, List<String>> en = iterator.next();
				for (String value : en.getValue()) {
					System.out.println(en.getKey() + ":" + value);
				}
			}

			// 获取返回流
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送请求出现异常！" + e);
			e.printStackTrace();

		} finally {
			// 关闭流
			try {
				if (printWriter != null) {
					printWriter.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Gson gson = new Gson();
		Map<String,Object> object = gson.fromJson(result, HashMap.class);
		return object;
	}

	/**
	 * URL参数拼接
	 * 
	 * @param params
	 * @return
	 */
	private static String toUrlParam(Map<String, Object> params, String requestMethod) {
		String urlParam = "";
		if (REQUEST_METHOD_GET.equals(requestMethod)) {
			if (params.size() > 0 && params != null) {
				urlParam += "?";
				for (String key : params.keySet()) {
					urlParam = urlParam + key + "=" + params.get(key) + "&";
				}
			}
			if (urlParam.endsWith("&")) {
				System.out.println(urlParam.lastIndexOf("&"));
				urlParam = urlParam.substring(0, urlParam.lastIndexOf("&"));
			}
		}
		return urlParam;
	}

	/**
	 * 转为Json数据
	 * 
	 * @param
	 * @return
	 * @throws JsonProcessingException
	 */
	private static String toJsonParam(Map<String, Object> params, String requestMethod) throws JsonProcessingException {
		String jsonParam = "";
		if (REQUEST_METHOD_POST.equals(requestMethod)) {
			ObjectMapper mapper = new ObjectMapper();
			jsonParam = mapper.writeValueAsString(params);
		}
		return jsonParam;
	}

}
