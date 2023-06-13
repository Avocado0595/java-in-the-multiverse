package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64Img {
public static String base64Img(String imgName) {
	byte[] imageBytes;
	try {
		imageBytes = Files.readAllBytes(Paths.get(imgName));
		return Base64.getEncoder().encodeToString(imageBytes);
	} catch (IOException e) {
		return null;
	}
}
}
