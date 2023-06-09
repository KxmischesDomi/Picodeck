package xyz.kxmischesdomi.picodeck.config;

import com.google.gson.JsonObject;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class Uploads {

	public static record Upload(String name, String base64) {

		public String base64AsSrc() {
			return String.format("url('data:image/png;base64,%s')", getUploadAsBase64(name));
		}

	}

	private static final JsonObject uploads;

	public static Upload getUpload(String name) {
		return new Upload(name, getUploadAsBase64(name));
	}

	public static String getUploadAsBase64(String name) {
		JsonObject data = uploads.getAsJsonObject(name);
		if (data == null) return "";
		return data.get("base64").getAsString();
	}

	public static JsonObject getUploads() {
		return uploads;
	}

	static {
		JsonObject json = Config.getDeviceConfig().getJson();
		JsonObject jsonObject = json.getAsJsonObject("uploads");
		if (jsonObject == null) {
			uploads = new JsonObject();
			json.add("uploads", uploads);
		} else {
			uploads = jsonObject;
		}
	}

}
