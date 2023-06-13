package util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.io.IOException;
import java.io.InputStream;

import com.auth0.jwt.algorithms.Algorithm;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
public class AlgorithmJWT {
	
 public static Algorithm getAlgorithm() throws IOException, InvalidKeySpecException {
	try {
		
		//Path pathPrivate = Path.of(AlgorithmJWT.class.getResourceAsStream("/key.priv") + "/key.priv");
		InputStream is =  AlgorithmJWT.class.getResourceAsStream("/key.priv");
		
		byte[] privateKeyBytes =is.readAllBytes();//Files.readAllBytes(pathPrivate);
		is.close();
		
		PKCS8EncodedKeySpec  ks = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = kf.generatePrivate(ks);

		is =  AlgorithmJWT.class.getResourceAsStream("/key.pub");
		byte[] publicKeyBytes = is.readAllBytes();//Files.readAllBytes(publicKeyFile.toPath());
		is.close();
		KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = publicKeyFactory.generatePublic(publicKeySpec);
		
		
		return Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
		
 }
}
