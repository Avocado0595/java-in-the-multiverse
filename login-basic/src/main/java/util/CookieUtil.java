package util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {
	public static Cookie isCookieExist(HttpServletRequest request) {
		Cookie[] c = request.getCookies();
		if(c==null || c.length==0) {
			return null;
		}

		Cookie token = null;
		for(Cookie i:c) {
			if(i.getName().equals("token")){
				token = i;
			}
		}
		return token;
	}
}
