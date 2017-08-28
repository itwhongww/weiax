package conan.weiax.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import conan.weiax.config.annotation.Read;
import conan.weiax.config.annotation.Write;
import conan.weiax.dao.MenuMapper;
import conan.weiax.exception.GlobalException;

@Controller
public class MenuService {
	private static Logger log = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	private MenuMapper menuMapper;
	@Value("${fileOutPath}")
	private String fileOutPath;
	@Value("${dbFilePath}")
	private String dbFilePath;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Write
	public void saveMenu(JSONObject json) {
		menuMapper.insertMenu(json);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public JSONObject findMenuById(int menuId) {
		JSONObject json = menuMapper.findMenuById(menuId);

		// 处理imgPath
		JSONArray array = new JSONArray();
		String imgPath = json.getString("imgPath");
		String[] paths = imgPath.split(",");
		for (String path : paths) {
			array.add(path);
		}
		json.put("imgPath", array);
		return menuMapper.findMenuById(menuId);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Read
	public List<JSONObject> findMenus() {
		return menuMapper.findMenus();
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Write
	public void updateMenu(JSONObject json) {
		menuMapper.updateMenu(json);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	@Write
	public void uploadImageAndInsertMenu(MultipartFile[] files, JSONObject json) {
		menuMapper.insertMenu(json);
		int id = json.getIntValue("menuId");
		StringBuilder dbPathSb = new StringBuilder();
		int count = 0;
		try {
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				StringBuilder ioPathSb = new StringBuilder();
				String ioDirPath = ioPathSb.append(fileOutPath)
						.append("love-kitchen/").append(id).toString();
				StringBuilder ioFileNameSb = new StringBuilder();
				String ioFileName = ioPathSb.append("/").append(id).append("_")
						.append(count).append(suffix).toString();
				String dbFileName = ioFileNameSb.append(id).append("_")
						.append(count).append(suffix).toString();
				dbPathSb.append(dbFilePath).append("love-kitchen/").append(id)
						.append("/").append(dbFileName).append(",");
				File newFile = new File(ioFileName);
				File newDirPath = new File(ioDirPath);
				if (!newDirPath.exists()) {
					newDirPath.mkdirs();
				}
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				BufferedInputStream in = new BufferedInputStream(
						file.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(newFile));
				int i;
				while ((i = in.read()) != -1) {
					out.write(i);
				}
				out.flush();
				out.close();
				in.close();
				count++;
			}
		} catch (FileNotFoundException e) {
			throw new GlobalException("文件不存在");
		} catch (IOException e) {
			log.info("==========io:", e);
			throw new GlobalException("文件读写有误");
		}
		if (dbPathSb.length() > 0) {
			json.put("menuId", id);
			json.put("imgPath", dbPathSb.substring(0, dbPathSb.length() - 1));
			menuMapper.updateMenu(json);
		}

	}
}
