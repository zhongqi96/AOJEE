package cn.artobj.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpDownloader {
	private final static String tag="HttpDownloader";
	private URL url = null;
	private HttpURLConnection urlConn;
	
	public void openConn(String urlStr) throws IOException
	{
		url = new URL(urlStr);
		// 创建一个Http连接
		urlConn = (HttpURLConnection) url
				.openConnection();
		urlConn.connect();
	}
	

	/**
	 * 根据URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容
	 * 1.创建一个URL对象
	 * 2.通过URL对象，创建一个HttpURLConnection对象
	 * 3.得到InputStram
	 * 4.从InputStream当中读取数据
	 * @param urlStr
	 * @return
	 */
	public String download(String urlStr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			// 创建一个URL对象
			openConn(urlStr);
			// 使用IO流读取数据
			buffer = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				buffer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

//	/**
//	 * 该函数返回整形 -1：代表下载文件出错 0：代表下载文件成功 1：代表文件已经存在
//	 */
//	public int downFile(String urlStr, String path, String fileName) {
//		InputStream inputStream = null;
//		try {
//			FileUtils fileUtils = new FileUtils(path);
//
//			if (fileUtils.isFileExist(fileName)) {
//				return 1;
//			} else {
//				openConn(urlStr);
//				inputStream = getInputStream();
//				File resultFile = fileUtils.write2SDFromInput(fileName, inputStream);
//				if (resultFile == null) {
//					return -1;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		} finally {
//			try {
//				inputStream.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 0;
//	}
//
//	/**
//	 * 该函数返回整形 -1：代表下载文件出错 -2保存文件错误 0：代表下载文件成功 1：代表文件已经存在
//	 */
//	public int downFile(String urlStr, String path, String fileName,Handler handler) {
//
//		InputStream inputStream = null;
//		try {
//
//			FileUtils fileUtils = new FileUtils(path);
//			Log.d(tag,urlStr);
//			Log.d(tag,fileName);
//			Log.d(tag,(fileUtils.isFileExist(fileName))+"");
//			if (fileUtils.isFileExist(fileName)) {
//				return 1;
//			} else {
//				openConn(urlStr);
//				handler.sendMessage(handler.obtainMessage(3,urlConn.getContentLength()+""));
//				inputStream = getInputStream();
//				File resultFile = fileUtils.write2SDFromInput(fileName, inputStream,handler);
//				if (resultFile == null) {
//					return -2;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		} finally {
//			try {
//				inputStream.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 0;
//	}

	/**
	 * 根据URL得到输入流
	 * 
	 * @param urlStr
	 * @return
	 * @throws java.net.MalformedURLException
	 * @throws java.io.IOException
	 */
	public InputStream getInputStream(){
		InputStream inputStream=null;
		try {
			inputStream = urlConn.getInputStream();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return inputStream;
	}
	
	
}
