package com.cykj.base.common.upload;

import com.cykj.base.common.model.Json;
import com.cykj.base.common.model.fileupload.FileUploadData;

/**
 * 文件上传基类
 * 
 * @author LeiYong
 *
 */
public abstract class FileUploadSuper {
	/**
	 * 上传文件
	 */
	public abstract Json uploadFile(FileUploadData fileUploadData);
}
