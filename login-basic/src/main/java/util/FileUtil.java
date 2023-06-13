package util;

public class FileUtil {
public static String getExtension(String fileName) {
	String[] parts = fileName.split("\\.");
	return "."+parts[parts.length-1];
}
}
