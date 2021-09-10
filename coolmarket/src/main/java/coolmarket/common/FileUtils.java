package coolmarket.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import coolmarket.dto.ImgDto;

@Component
public class FileUtils {
	public List<ImgDto> parseFileInfo(String adNo, String marNo, String comNo, MultipartHttpServletRequest images)
			throws Exception {

		if (ObjectUtils.isEmpty(images)) {
			return null;
		}

		List<ImgDto> fileList = new ArrayList<>();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File("/home/ec2-user/coolmarket/" + path);
		
		if (file.exists() == false) {
			file.mkdirs();
		}

		Iterator<String> iterator = images.getFileNames();

		String newFileName;
		String originalFileExtension;
		String contentType;

		while (iterator.hasNext()) {
			List<MultipartFile> list = images.getFiles(iterator.next());

			for (MultipartFile multiFile : list) {
				if (multiFile.isEmpty() == false) {
					contentType = multiFile.getContentType();
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}

					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

					ImgDto img = new ImgDto();
					if (adNo.equals("")) {
						img.setAdNo(null);
					} else {
						img.setAdNo(adNo);
					}
					if (marNo.equals("")) {
						img.setMarNo(null);
					} else {
						img.setMarNo(marNo);
					}
					if (comNo.equals("")) {
						img.setComNo(null);
					} else {
						img.setComNo(comNo);
					}
					img.setImgSize(multiFile.getSize());
					img.setOriImgName(multiFile.getOriginalFilename());
					img.setStoredImgPath(path + "/" + newFileName);
					fileList.add(img);

					file = new File(path + "/" + newFileName);
					multiFile.transferTo(file);
				}
			}
		}

		return fileList;
	}
}
