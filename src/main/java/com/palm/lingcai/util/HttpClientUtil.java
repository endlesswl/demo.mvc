package com.palm.lingcai.util;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * HttpClient 文件流上传、下载
 * @author LDL
 * 2014年12月11日
 */
public class HttpClientUtil {

	/**
	 * 文件流上传
	 * @param targetURL
	 * @param targetFile
	 * @return
	 */
	public static boolean upload(String targetURL, File targetFile) {
		PostMethod filePost = new PostMethod(targetURL);
		try {
			//filePost.setParameter("name", value);其他参数
			Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts,filePost.getParams()));

			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
			int status = client.executeMethod(filePost);

			if (status == HttpStatus.SC_OK) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
		return false;
	}
	
	/**
	 * 接收文件流
	 * @param request
	 * @param saveFile
	 * @return
	 */
	public static boolean getStreamToFile(HttpServletRequest request,String saveFile){
		InputStream inputStream = null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();//
			factory.setSizeThreshold(4096);// 设置缓冲区大小，这里是4kb
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024000);// 设置上传文件大小
			List<FileItem> items = upload.parseRequest(request);// 获取所有文件
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fileItem = i.next();
				String fileName = fileItem.getName();
				if (fileName != null && !fileName.equals("")) {
					inputStream = fileItem.getInputStream();
					FileUtils.copyInputStreamToFile(inputStream, new File(saveFile));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return false;
	}

	public static void main(String args[]) {
		String targetURL = null;// TODO 指定URL
		File targetFile = null;// TODO 指定上传文件

		targetFile = new File("1.mp3");
		targetURL = "http://localhost:8080/test/tt"; // servleturl
		PostMethod filePost = new PostMethod(targetURL); // 若没有commons-codec-1.4-bin.zip,

		try {
			filePost.setParameter("name", "中文");

			Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts,
					filePost.getParams()));

			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams()
					.setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);

			if (status == HttpStatus.SC_OK) {
				System.out.println("上传成功");
				// 上传成功
			} else {
				System.out.println("上传失败");
				// 上传失败
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
	}
}