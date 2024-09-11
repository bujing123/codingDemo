//package com.jd.ljy;
//
//import com.icbc.api.DefaultIcbcClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * Title: 使用spring集成的方式调用JSF服务<br>
// *
// * Description: <br>
// *
// * Company: <a href=www.jd.com>京东</a><br>
// *
// * @author <a href=mailto:zhanggeng@jd.com>章耿</a>
// */
//public class ClientMain {
//
//	/**
//	 * SLF4J logger
//	 */
//	private final static Logger LOGGER = LoggerFactory
//			.getLogger(ClientMain.class);
//
//	/**
//	 * Method Name main
//	 *
//	 * @param args
//	 *            Return Type void
//	 */
//	public static void main(String[] args) {
//
//		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
//                "/jsf-consumer.xml");
//
//		HelloService service = (HelloService) appContext
//				.getBean("helloService");
//		LOGGER.info("得到调用端代理：{}", service);
//
//		while (true) {
//			try {
//				String result = service.echoStr("zhanggeng put");
//				LOGGER.info("response msg from server :{}", result);
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//
//			try {
//				Thread.sleep(2000);
//			} catch (Exception e) {
//			}
//		}
//
//		// SAFContext.destroy();
//
//
//		// 构造client对象
//		DefaultIcbcClient client = new DefaultIcbcClient(APP_ID, MY_PRIVATE_KEY, APIGW_PUBLIC_KEY);
//// 设置请求对象request
//		EbankcVerifiedInfoQueryRequest request = new EbankcVerifiedInfoQueryRequest();
//// 设置请求路径
//// 生产请求路径：https://gw.open.icbc.com.cn/api/+接口服务地址
//// 沙箱测试请求路径：https://gw-sandbox.open.icbc.com.cn/api/+接口服务地址
//		request.setServiceUrl("https://gw.open.icbc.com.cn/api/ebankc/V1/VerifiedInfoQuery");
//// 设置业务参数，每个Request请求实现类都有一个RequestBiz内部类用来设置业务参数
//		EbankcVerifiedInfoQueryRequestBiz bizContent= new EbankcVerifiedInfoQueryRequestBiz();
//		bizContent.setNextTag("");
//		bizContent.setTranDateBegin("10160910");
//		bizContent.setTranDateEnd("30170920");
//		bizContent.setVerifiedCorpId("0200EG0000602");
//		bizContent.setVerifiedId("");
//		request.setBizContent(bizContent);
//// request组装完成，开始发起调用
//		EbankcVerifiedInfoQueryResponse response = client.execute(request, "msgId");
//// 判断调用是否成功，进行后续业务处理
//		if (response.isSuccess()) {
//			// TODO 业务成功处理
//			System.out.println(response.getReturnMsg());
//		} else {
//			// TODO 失败
//			System.out.println(response.getReturnMsg());
//		}
//	}
//}
