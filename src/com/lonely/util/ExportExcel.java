package com.lonely.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ExportExcel {

	public static void export(String templatePath, Map<String, Object> dataMap, String buildFile, String newName) {
		try {
			/*
			 * ActionContext ac = ActionContext.getContext(); HttpServletRequest
			 * request = (HttpServletRequest)
			 * ac.get(ServletActionContext.HTTP_REQUEST); HttpServletResponse
			 * response = (HttpServletResponse)
			 * ac.get(ServletActionContext.HTTP_RESPONSE); ServletContext
			 * application = request.getSession().getServletContext();
			 */
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			String path = SysConstant.FREEMARK_PATH;
			configuration.setDirectoryForTemplateLoading(new File(path));// 此处是本类Class.getResource()相对于模版文件的相对路径
			Template template = null;
			File outFile = new File(buildFile);
			Writer writer = null;
			template = configuration.getTemplate(templatePath);
			template.setEncoding("utf-8");
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(outFile), Charset.forName("utf-8")));// 此处为输
																										// 出文档编码
			template.process(dataMap, writer);
			writer.flush();
			writer.close();

			// return true;

			// 设置response的编码方式
			// response.setContentType("application/x-msdownload");
			// 设置附加文件名
			// response.setHeader("Content-Disposition",
			// "attachment;filename=" + new String(newName.getBytes("utf-8"),
			// "iso-8859-1"));

			// 读出文件到i/o流
			FileInputStream fis = new FileInputStream(outFile);
			BufferedInputStream buff = new BufferedInputStream(fis);

			byte[] b = new byte[1024];// 相当于我们的缓存

			long k = 0;// 该值用于计算当前实际下载了多少字节

			// 从response对象中得到输出流,准备下载

			String exportExcelName = new String(newName.getBytes("utf-8"), "iso-8859-1");
			OutputStream myout = new FileOutputStream(new File(SysConstant.EXPORT_EXCEL_PATH) + exportExcelName);

			// 开始循环下载

			while (k < outFile.length()) {

				int j = buff.read(b, 0, 1024);
				k += j;

				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);

			}

			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();
			myout.close();

		} catch (Exception e) {

			e.printStackTrace();
			// return false;
		}
	}

	public static void export(String templatePath, Map<String, Object> dataMap, String buildFile) {
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			String path = SysConstant.FREEMARK_PATH;
			configuration.setDirectoryForTemplateLoading(new File(path));// 此处是本类Class.getResource()相对于模版文件的相对路径
			Template template = null;
			File outFile = new File(SysConstant.EXPORT_EXCEL_PATH + buildFile);
			Writer writer = null;
			template = configuration.getTemplate(templatePath);
			template.setEncoding("utf-8");
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(outFile), Charset.forName("utf-8")));// 此处为输
																										// 出文档编码
			template.process(dataMap, writer);
			writer.flush();
			writer.close();

		} catch (Exception e) {

			e.printStackTrace();
			// return false;
		}
	}

}
