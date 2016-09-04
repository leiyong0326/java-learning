package com.cykj.base.common.upload;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cykj.base.common.em.ext.FileTypeEnum;
import com.cykj.base.common.util.MD5Util;
import com.cykj.base.common.util.StringUtil;

public class UpYunUploadUtil {

	/**
	 * 默认上传域名
	 */
	public static final String DEFAULT_UPLOAD_URL = "http://v0.api.upyun.com/";

	/**
	 * 默认上传方式
	 */
	public static final String DEFAULT_UPLOAD_METHOD = "PUT";

	/**
	 * 图片上传大小常量 2M 2*1024*2014 = 2097152 Bytes(B) not bits
	 */
	public static final Long UPLOAD_IMG_SIZE = 2097152L;

	/**
	 * 图片upyun空间名称
	 */
	public static final String UPYUN_PIC_BUCKET_NAME = "cy-carimg-files";

	/**
	 * upyun账户名称
	 */
	public static final String UPYUN_OPERATOR_NAME = "qhcykjadmin";// qhcykj

	/**
	 * upyun账户密码
	 */
	public static final String UPYUN_PASSWORD = "qhcykj2016";// Cheyun2016

	/**
	 * 上传文件访问路径前缀 最终访问路径为 http://cy-carimg-files.b0.upaiyun.com/img/1.jpg
	 */
	public static final String FILE_ACCESS_URL = "http://" + UPYUN_PIC_BUCKET_NAME + ".b0.upaiyun.com/";

	/**
	 * 空间表单api 秘钥,upyun后台高级功能里面设置
	 */
	public static final String UPYUN_FORM_API_SECRET = "f5vVO3Kh64nV648Vuk2yaml4XYI=";

	/**
	 * 默认授权有效期，Unix 时间戳
	 */
	public static final long DEFAULT_EXPIRATION = 30 * 60 * 1000;// 30分钟

	/**
	 * 默认上传文件的分块数
	 */
	public static final int DEFAULT_FILE_BLOCKS = 1;

	public static final String FILE_SECRET = "qhcy";
	
	public static final String CAR_IMG_FOLDER="car_img";//新车图片目录
	
	public static final String CAR_OLD_IMG_FOLDER="old_car_img";//二手车图片目录
	
	public static final String PARTS_IMG_FOLDER="parts_img";//精品配件图片目录
	
	public static final String ORTHER_IMG="img";//其他图片目录
	
	public static final String CAR_DETAIL_HTML_FOLDER="car_detail_html";//新车配置详情目录

	public static final String PARTS_DETAIL_HTML_FOLDER="parts_detail_html";//精品详情目录

	private static UpYunUploadUtil uploadUtil;

	public synchronized static UpYunUploadUtil getInstance() {
		if (uploadUtil == null) {
			uploadUtil = new UpYunUploadUtil();
		}
		return uploadUtil;
	}

	public UpYunToken getUpYunToken(String path, String extendName, long fileSize, String fileMD5) {
		long expiration = (new Date().getTime() + DEFAULT_EXPIRATION) / 1000;// 有效期限
		String fileName = expiration + StringUtil.getNonceStr(6) + "." + extendName;
		path = "/" + path + "/" + fileName;
		StringBuffer  signature=new StringBuffer("expiration");
		signature.append(expiration)
			.append("file_blocks").append(DEFAULT_FILE_BLOCKS)
			.append("file_hash") .append(fileMD5)
			.append("file_size").append(fileSize)
			.append("path").append(path)
			.append(UPYUN_FORM_API_SECRET);
		
		String md5=MD5Util.md5(signature.toString());
		
		
		UpYunToken token=new UpYunToken();
		token.setBucket(UPYUN_PIC_BUCKET_NAME);
		token.setExpiration(expiration);
		token.setSignature(md5);
		token.setPath(path);
		token.setAccessPrefix(FILE_ACCESS_URL);
		return token;
	}

	public String uploadFileToUpyun(MultipartFile Img, String folderName,String extendName, String picRemarkCode) {
		String address = "";
		String errorMsg = "";

		UpYun upyun = new UpYun(UPYUN_PIC_BUCKET_NAME, UPYUN_OPERATOR_NAME, UPYUN_PASSWORD);
		String dirRoot = "/";
		// 创建文件夹
		// TO-do 图片名称在后面加标识符
		String newFileName = String.valueOf(System.currentTimeMillis()) + picRemarkCode + "." + extendName;
		// 传到upyun后的文件路径
		String filePath = dirRoot + folderName + dirRoot + newFileName;

		try {
			// upyun.setContentMD5(upyun.md5(picFile));
			// 文件密钥
			upyun.setFileSecret(FILE_SECRET);
			byte[] imgData = Img.getBytes();
			boolean result = upyun.writeFile(filePath, imgData, true);
			if (result = true) {
				// ！ 是upyun制定的文件识别分隔符，可以去upyun服务器去修改
				address = FILE_ACCESS_URL + filePath + "!" + FILE_SECRET;
				System.out.println(address + "--------" + result);
			}
		} catch (IOException e) {
			errorMsg = "上传图片到upyun失败";
			e.printStackTrace();
		}
		return (StringUtils.isBlank(errorMsg)) ? address : errorMsg;
	}

	public String verifyImg(MultipartFile communityImg) {
		// 校验后返回的结果，包含errorMsg以及后缀名extendName
		String errorMsg = "";
		String extendName = "";

		String fileName = communityImg.getOriginalFilename();
		// 获取拓展名,并转化为小写
		extendName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();

		if (communityImg.getSize() > UPLOAD_IMG_SIZE) {
			errorMsg = "图片大小超过2M，请重新上传";
			return errorMsg;
		} else if (!FileTypeEnum.Img.getValue().contains(extendName)) {
			errorMsg = "图片类型非jpg或jpeg或png";
		}

		return StringUtils.isBlank(errorMsg) ? extendName : errorMsg;
	}
	
	
	/**
	 * 
	 * @param htmlContent 页面内容
	 * @param url  原url地址
	 * @param htmlFloder 保存目录
	 * @return
	 */
	public String uploadHtml(String htmlContent,String url,String htmlFloder){
		String address = "";
		UpYun upyun = new UpYun(UPYUN_PIC_BUCKET_NAME, UPYUN_OPERATOR_NAME, UPYUN_PASSWORD); 
		String dirRoot = "/";
		// 创建文件夹
		// TO-do 图片名称在后面加标识符
		// 传到upyun后的文件路径
		String filePath="";
		if (StringUtils.isBlank(url)) {
			filePath = dirRoot.concat(htmlFloder) .concat(dirRoot).concat(StringUtil.getNonceStr(6)+System.currentTimeMillis()).concat(".html");
		}else{
			filePath=url.replace(FILE_ACCESS_URL, "");
		}
		
		// upyun.setContentMD5(upyun.md5(picFile));
		
		byte[] imgData = htmlContent.getBytes();
		boolean result = upyun.writeFile(filePath, imgData, true);
		if (result = true) {
			// ！ 是upyun制定的文件识别分隔符，可以去upyun服务器去修改
			address = FILE_ACCESS_URL + filePath;
			System.out.println(address + "--------" + result);
		}
		
		return address;
	}

}
