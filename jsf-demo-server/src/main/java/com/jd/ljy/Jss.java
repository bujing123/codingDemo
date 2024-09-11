package com.jd.ljy;


import com.jd.jss.Credential;
import com.jd.jss.JingdongStorageService;
import com.jd.jss.client.ClientConfig;
import com.jd.jss.domain.StorageObject;
import com.jd.ldop.oms.rpc.dto.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: JssServiceImpl.java <br>
 * Description: 云存储服务<br>
 * Date: 2015-12-14 <br>
 * Copyright (c) 2015 jd <br>
 *
 */
@Repository
public class Jss{
    private final static Log logger = LogFactory.getLog(Jss.class);
    /**
     * 存储数据的最基本的单元
     */
    private String bucket;
    /**
     * 访问密钥
     */
    private String accesskey;
    /**
     * 安全密钥
     */
    private String secretkey;
    /**
     * 内网连接端点
     */
    private String endpoint;
    /**
     * 外网连接端点
     */
    private String domainEndpoint;
    /**
     * 指定域名端点
     */
    private String specifyEndpoint;
    /**
     * 服务器请求超时
     */
    private long connectionTimeout = 10000;
    /**
     * 服务器响应超时
     */
    private long socketTimeout = 10000;
    /**
     * 存放时间
     */
    private Integer storeTime = 2592000;

    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("第一列");
        arrayList.add("第二列");
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        dataList.add(arrayList);
        dataList.add(arrayList);
        File csvFile = null;
        InputStream stream = createCSVFile(arrayList, dataList, "测试文件300", csvFile);
        int length = 0;
        try {
            length = stream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Credential credential = new Credential("FR57BMTZmcj4QFdR", "tlH5q841yMMsqzbI0XRtdCfKcw9I50fmWmIHK0Xf");
        ClientConfig config = new ClientConfig();
        config.setEndpoint("storage.jd.local");
        config.setConnectionTimeout(1000);
        config.setSocketTimeout(10000);
        JingdongStorageService jss = new JingdongStorageService(credential, config);

        String bucket = "oms.jdwl";
        String bucketSuffix = ".common";
        String key = "测试文件200.csv";

        /**判断bucke是否存在*/
        boolean isHas = jss.hasBucket(bucket + bucketSuffix);
        if (!isHas) {
            jss.bucket(bucket + bucketSuffix).create();
        }
        jss.bucket(bucket + bucketSuffix).object(key).entity(length, stream).put();
        URI uri = jss.bucket(bucket + bucketSuffix).object(key).generatePresignedUrl(2592000);
        try {
            String url = String.valueOf(uri.toURL());
            System.out.println(url);
        } catch (MalformedURLException e) {
            System.out.println("uri转化出错");
        }
    }
    /**
     * CSV文件生成方法
     *
     * @param head     文件头
     * @param dataList 数据列表
     * @param filename 文件名
     * @return
     */
    public static  InputStream createCSVFile(List<Object> head, List<List<Object>> dataList, String filename, File csvFile) {
        BufferedWriter csvWtriter = null;
        InputStream stream = null;
        try {
            //csvFile = File.createTempFile(filename, ".csv");
            csvFile = new File("/Users/lijianyu1/需求/华硕/"+filename+".csv");
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);
            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
            byte[] bytes = file2Byte(csvFile.getCanonicalPath());
            InputStream is = new ByteArrayInputStream(bytes);
            return is;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != csvWtriter) {
                try {
                    csvWtriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    public static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            data=data+"\t";
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
    private static byte[] file2Byte(String filePath) {
        ByteArrayOutputStream bos = null;
        BufferedInputStream in = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("file not exists");
            }
            bos = new ByteArrayOutputStream((int) file.length());
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public void createBucket(String bucketSuffix) {
        JingdongStorageService jss = getJss();
        boolean isHas = jss.hasBucket(bucket + bucketSuffix);
        if (!isHas) {
            jss.bucket(bucket + bucketSuffix).create();
        }
    }


    public void deleteBucket(String bucketSuffix) {
        JingdongStorageService jss = getJss();
        jss.bucket(bucket + bucketSuffix).delete();
    }

    public Result<String> uploadResource(String bucketSuffix, String key, File file) {
        Result<String> result = new Result<String>();
        try {
            JingdongStorageService jss = getJss();
            /**判断bucke是否存在*/
            boolean isHas = jss.hasBucket(bucket + bucketSuffix);
            if (!isHas) {
                jss.bucket(bucket + bucketSuffix).create();
            }
            jss.bucket(bucket + bucketSuffix).object(key).entity(file).put();
            URI uri = jss.bucket(bucket + bucketSuffix).object(key).generatePresignedUrl(storeTime);
            String url = String.valueOf(uri.toURL());
            result.setResultCode(Result.SUCCESS);
            result.setData(url);
        } catch (MalformedURLException e) {
            result.setResultCode(Result.EXCEPTION);
            result.setResultMsg("URL转换异常");
            logger.error("JssServiceImpl.uploadResource.MalformedURLException:", e);
        } catch (Exception e) {
            result.setResultMsg("上传文件到JSS服务器异常");
            result.setResultCode(Result.EXCEPTION);
            logger.error("JssServiceImpl.uploadResource.Exception", e);
        }
        return result;
    }


    public Result<String> uploadResource(String bucketSuffix, String key, InputStream inputStream, long length) {
        Result<String> result = new Result<String>();
        try {
            JingdongStorageService jss = getJss();
            /**判断bucke是否存在*/
            boolean isHas = jss.hasBucket(bucket + bucketSuffix);
            if (!isHas) {
                jss.bucket(bucket + bucketSuffix).create();
            }
            jss.bucket(bucket + bucketSuffix).object(key).entity(length, inputStream).put();
            URI uri = jss.bucket(bucket + bucketSuffix).object(key).generatePresignedUrl(storeTime);
            String url = String.valueOf(uri.toURL());
            result.setResultCode(Result.SUCCESS);
            result.setData(url);
        } catch (MalformedURLException e) {
            result.setResultCode(Result.EXCEPTION);
            result.setResultMsg("URL转换异常");
            logger.error("JssServiceImpl.uploadResource.MalformedURLException:", e);
        } catch (Exception e) {
            result.setResultMsg("上传文件到JSS服务器异常");
            result.setResultCode(Result.EXCEPTION);
            logger.error("JssServiceImpl.uploadResource.Exception", e);
        }
        return result;
    }


    public Result<String> uploadExtranetResource(String bucketSuffix, String key, InputStream inputStream, long length) {
        Result<String> result = new Result<String>();
        try {
            JingdongStorageService jss = getJss();
            /**判断bucke是否存在*/
            boolean isHas = jss.hasBucket(bucket + bucketSuffix);
            if (!isHas) {
                jss.bucket(bucket + bucketSuffix).create();
            }
            jss.bucket(bucket + bucketSuffix).object(key).entity(length, inputStream).put();
            URI uri = jss.bucket(bucket + bucketSuffix).object(key).generatePresignedUrl(storeTime,specifyEndpoint);
            String url = String.valueOf(uri.toURL());
            result.setResultCode(Result.SUCCESS);
            result.setData(url);
        } catch (MalformedURLException e) {
            result.setResultCode(Result.EXCEPTION);
            result.setResultMsg("URL转换异常");
            logger.error("JssServiceImpl.uploadResource.MalformedURLException:", e);
        } catch (Exception e) {
            result.setResultMsg("上传文件到JSS服务器异常");
            result.setResultCode(Result.EXCEPTION);
            logger.error("JssServiceImpl.uploadResource.Exception", e);
        }
        return result;
    }



    public Result<String> uploadResource(String bucketSuffix, String key, File file, String fileName) {
        Result<String> result = new Result<String>();
        try {
            JingdongStorageService jss = getJss();
            /**判断bucke是否存在*/
            boolean isHas = jss.hasBucket(bucket + bucketSuffix);
            if (!isHas)
                jss.bucket(bucket + bucketSuffix).create();
            jss.bucket(bucket + bucketSuffix).object(key).entity(file).disposition(fileName).put();
            URI uri = jss.bucket(bucket + bucketSuffix).object(key).generatePresignedUrl(storeTime);
            String url = String.valueOf(uri.toURL());
            result.setResultCode(Result.SUCCESS);
            result.setData(url);
        } catch (MalformedURLException e) {
            result.setResultCode(Result.EXCEPTION);
            result.setResultMsg("URL转换异常");
            logger.error("JssServiceImpl.uploadResource.MalformedURLException:", e);
        } catch (Exception e) {
            result.setResultMsg("上传文件到JSS服务器异常");
            result.setResultCode(Result.EXCEPTION);
            logger.error("JssServiceImpl.uploadResource.Exception", e);
        }
        return result;
    }


    public String uploadUrlPath(String bucketSuffix, String key) {
        String result = "";
        try {
            JingdongStorageService jss = getJss();
            /**判断bucke是否存在*/
            boolean isHas = jss.hasBucket(bucket + bucketSuffix);
            if (!isHas)
                jss.bucket(bucket + bucketSuffix).create();
            URI uri = jss.bucket(bucket + bucketSuffix).object(key).generatePresignedUrl(storeTime);
            result = String.valueOf(uri.toURL());
        } catch (MalformedURLException e) {
            logger.error("JssServiceImpl.uploadUrlPath.MalformedURLException:", e);
        } catch (Exception e) {
            logger.error("JssServiceImpl.uploadUrlPath.Exception", e);
        }
        return result;
    }


    public StorageObject getStorageObject(String bucketSuffix, String key) {
        StorageObject result = null;
        try {
            JingdongStorageService jss = getJss();
            result = jss.bucket(bucket + bucketSuffix).object(key).get();
        } catch (Exception e) {
            logger.error("JssServiceImpl.uploadResource.Exception", e);
        }
        return result;
    }


    public StorageObject getStorageObject(String bucketSuffix, String key, String fileName) {
        StorageObject result = null;
        try {
            JingdongStorageService jss = getJss();
            result = jss.bucket(bucket + bucketSuffix).object(key).disposition(fileName).get();
        } catch (Exception e) {
            logger.error("JssServiceImpl.uploadResource.Exception", e);
        }
        return result;
    }

    private JingdongStorageService getJss() {
        Credential credential = new Credential(accesskey, secretkey);
        ClientConfig config = new ClientConfig();
        config.setEndpoint(endpoint);
        config.setConnectionTimeout(1000);
        config.setSocketTimeout(10000);
        return new JingdongStorageService(credential, config);
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndPoint() {
        return getEndpoint();
    }

    public String getDomainEndpoint() {
        return domainEndpoint;
    }

    public void setDomainEndpoint(String domainEndpoint) {
        this.domainEndpoint = domainEndpoint;
    }


    public String getDomainEndPoint() {
        return getDomainEndpoint();
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(long socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Integer storeTime) {
        this.storeTime = storeTime;
    }


    public Long getExpireTime() {
        return Long.valueOf(storeTime.toString());
    }

    public String getSpecifyEndpoint() {
        return specifyEndpoint;
    }

    public void setSpecifyEndpoint(String specifyEndpoint) {
        this.specifyEndpoint = specifyEndpoint;
    }



    public void deleteBucketObject(String bucketSuffix, String key) {
        // TODO Auto-generated method stub
        JingdongStorageService jss = getJss();
        boolean exist = jss.bucket(bucket + bucketSuffix).object(key).exist();
        if (exist) {
            jss.bucket(bucket + bucketSuffix).object(key).delete();
        }
    }


}
