package com.taobao.muming.Util.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientFluentUtil {
	private static final int TIMEOUT_SECONDS = 2;
	private static final int POOL_SIZE = 200;
	private static CloseableHttpClient httpClient;

	public void init() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(TIMEOUT_SECONDS * 1000)
				.setConnectTimeout(TIMEOUT_SECONDS * 1000).build();

		httpClient = HttpClientBuilder.create().setMaxConnTotal(POOL_SIZE)
				.setMaxConnPerRoute(POOL_SIZE)
				.setDefaultRequestConfig(requestConfig).build();
	}

	public void destroy() {
		try {
			httpClient.close();
		} catch (IOException e) {

		}
	}

	public static String excuteGet(String contentUrl) throws Exception {
		Executor executor = Executor.newInstance(httpClient);
		Response response = executor.execute(Request.Get(contentUrl));
		String returnStr = response.returnContent().asString();
		return returnStr;
	}

	public static String excutePost(String contentUrl,
			Map<String, String> params, ContentType contentType)
			throws Exception {
		String returnObj = null;
		if (!StringUtils.isEmpty(contentUrl)) {
			Executor executor = Executor.newInstance(httpClient);
			// if (contentType == null) {
			// contentType = ContentType.create("application/json");
			// }

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}

			try {
				returnObj = executor
						.execute(
								Request.Post(contentUrl)
										.body(new UrlEncodedFormEntity(nvps,
												"UTF-8"))).returnContent()
						.asString();
				// String ret = returnObj.handleResponse(new
				// ResponseHandler<String>() {
				// @Override
				// public String handleResponse(HttpResponse response)
				// throws ClientProtocolException, IOException {
				// StatusLine statusLine = response.getStatusLine();
				// return null;
				// }
				// });
			} catch (Exception e) {
				try {
					returnObj = executor
							.execute(
									Request.Post(contentUrl).body(
											new UrlEncodedFormEntity(nvps,
													"UTF-8"))).returnContent()
							.asString();
				} catch (Exception ex) {
					throw ex;
				}
			}
		}
		return returnObj;
	}

	// 重试机制代码，参考

	// public static void main(String[] args) throws Exception {
	//
	// String url = "http://localhost/jiaowu/?dsdf=sdfd";
	// DefaultHttpClient client = new DefaultHttpClient();
	//
	// org.apache.http.params.HttpParams params = client.getParams();
	// HttpConnectionParams.setConnectionTimeout(params, 5 * 1000);
	// HttpConnectionParams.setSoTimeout(params, 10 * 1000);
	//
	// HttpGet request = new HttpGet(url);
	//
	// sendRequest(client,request,3);
	// }
	//
	// static void sendRequest(HttpClient client, HttpGet request, int count) {
	//
	// for (int i = 0; i < count; i++) {
	// try {
	// System.out.println(new Date().toLocaleString());
	// client.execute(request);
	// break;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }

}
