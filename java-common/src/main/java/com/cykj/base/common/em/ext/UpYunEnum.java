package com.cykj.base.common.em.ext;

import com.cykj.base.common.em.EnumSuper;
import com.cykj.base.common.upload.UpYunUploadUtil;

/**
 * 
 * @author TangFangguo
 * @date 2016年7月7日
 * @Description 又拍云上传文件枚举类
 */
public enum UpYunEnum implements EnumSuper{
	carImg(UpYunUploadUtil.CAR_IMG_FOLDER,"新车图片的上传保存目录"),
	
	oldCarImg(UpYunUploadUtil.CAR_OLD_IMG_FOLDER,"新车图片的上传保存目录 "),
	
	partsImg(UpYunUploadUtil.PARTS_IMG_FOLDER,"精品配件图片的上传保存目录 "),
	
	img(UpYunUploadUtil.ORTHER_IMG,"其他图片上传保存目录 "),
	
	carHtml(UpYunUploadUtil.CAR_DETAIL_HTML_FOLDER,"新车配置详情保存目录"),
	
	partsHtml(UpYunUploadUtil.PARTS_DETAIL_HTML_FOLDER,"汽车配件详情保存目录"),
	
	actHtml("act_html","活动内容保存目录"),
	;
	
	private String value;
	private String discription;

	UpYunEnum(String value, String discription) {
		this.value = value;
		this.discription = discription;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getDiscription() {
		return discription;
	}
}
