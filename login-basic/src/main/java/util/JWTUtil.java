package util;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import model.user.UserJWT;

public class JWTUtil {
	public static UserJWT verify(String value) throws InvalidKeySpecException, IOException {
		Algorithm algorithm = AlgorithmJWT.getAlgorithm();
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("https://login.com").build();
		DecodedJWT jwt = verifier.verify(value);
		if(jwt.getClaim("email")==null)
			return null;
		return new UserJWT(jwt.getClaim("email").asString(),jwt.getClaim("name").asString());
	}
}
