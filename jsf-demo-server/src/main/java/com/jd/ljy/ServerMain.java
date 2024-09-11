package com.jd.ljy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

/**
 * Title: 使用spring集成的方式发布JSF服务<br>
 *
 * Description: <br>
 *
 * Company: <a href=www.jd.com>京东</a><br>
 * 
 * @author <a href=mailto:zhanggeng@jd.com>章耿</a>
 */
public class ServerMain {

	/**
	 * SLF4J logger
	 */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ServerMain.class);

	/**
	 * Method Name main
	 * 
	 * @param args
	 *            Return Type void
	 */
	public static void main(String[] args) throws IOException {

//		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
//                "/jsf-provider.xml");
//
//		LOGGER.info("服务端启动完成！");
//
//		// 关闭服务
//		// appContext.close();
//
//		// 启动本地服务，然后hold住本地服务
//		synchronized (ServerMain.class) {
//			while (true) {
//				try {
//					ServerMain.class.wait();
//				} catch (InterruptedException e) {
//				}
//			}
//		}
		String s = "lijianyu";
		File f = new File("/Users/lijianyu1/"+s+".txt");
		//写文件
		FileOutputStream fop = new FileOutputStream(f,true);    //false是覆盖写，true是追加写
		OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
		for(int i=0 ; i<5; i++){
			writer.append("要写到本地的字符串"+"\r\n");
		}
		// 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入
		writer.close();
		fop.close();
	}
}