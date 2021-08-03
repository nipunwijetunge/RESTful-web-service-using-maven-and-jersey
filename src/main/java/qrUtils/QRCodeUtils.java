package qrUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import dbResource.Response;

public class QRCodeUtils {
	
	public static Response createCode(String content) {
		int width = 200;
		int height = 200;
		
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MARGIN, 0);
		
		BitMatrix bitMatrix = null;
		
		try {
			bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", bos);
			
			return new Response(Response.SUCCESS, "SUCCESS", "Package Successfully registered!", new String(Base64.getEncoder().encode(bos.toByteArray()), "UTF-8"));
			
		} catch (Exception e) {
			Response response = new Response(Response.ERROR, "FAILED", "Something's wrong!");
			e.printStackTrace();
			return response;
		}
	}
	
	private static BitMatrix deleteWhites(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;
		
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		
		for(int i = 0; i < resWidth; i++) {
			for(int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}
	
	public static void decodeImage(String baseTxt, String savePath) throws Exception {
		byte[] data = Base64.getDecoder().decode(baseTxt);
		
		FileOutputStream fos = new FileOutputStream(savePath);
		fos.write(data);
		fos.close();
	}
	
	public static void main(String[] args) throws Exception {
		decodeImage(createCode("2021/EL/00001").getPackageRegistrationNoQR().getPackageRegistrationNoQR(), "C:\\Users\\Nipun\\Desktop\\image.png");
	}
}
