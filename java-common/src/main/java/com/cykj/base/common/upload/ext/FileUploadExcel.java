package com.cykj.base.common.upload.ext;

import com.cykj.base.common.model.Json;
import com.cykj.base.common.model.fileupload.FileUploadData;
import com.cykj.base.common.upload.FileUploadSuper;

/**
 * Excel上传
 * @author LeiYong
 *
 */
public class FileUploadExcel extends FileUploadSuper {
	private static FileUploadExcel uploadExcel;
	@Override
	public Json uploadFile(FileUploadData fileUploadData) {
		// TODO Auto-generated method stub
		return null;
	}

	public synchronized static FileUploadExcel getInstance(){
		if (uploadExcel==null) {
			uploadExcel = new FileUploadExcel();
		}
		return uploadExcel;
	}

}
