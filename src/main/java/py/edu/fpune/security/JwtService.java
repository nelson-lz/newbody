package py.edu.fpune.security;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import py.edu.fpune.rest.exceptions.JwtException;

@Service
public class JwtService {

	public static final String BEARER ="Bearer";
	public static final String USER ="user";
	public static final String ROLES ="roles";
	public static final String ISSUER ="fpune-spring5";
	public static final int EXPIRES_IN_MILLISECOND =3600000;
	public static final String SECRET ="clave-secreta-test";
	
	public String createToken(String user, List<String> roles) {
		return JWT.create()
				  .withIssuer(ISSUER)
				  .withIssuedAt(new Date())
				  .withNotBefore(new Date())
				  .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRES_IN_MILLISECOND))
				  .withClaim(USER, user)
				  .withArrayClaim(ROLES, roles.toArray(new String[0]))
				  .sign(Algorithm.HMAC256(SECRET));
	}
	
	public boolean isBearer(String authorization) {
		return authorization !=null && authorization.startsWith(BEARER) && authorization.split("\\.").length ==3;
	}
	
	public String  user(String authorization) throws JwtException {
		return this.verify(authorization).getClaim(USER).asString();
	}
	
	private DecodedJWT verify(String authorization) throws JwtException{
		if(!this.isBearer(authorization)) {
			throw new JwtException("It is not Berear");
		}
		try {
			return JWT.require(Algorithm.HMAC256(SECRET))
					  .withIssuer(ISSUER).build()
					  .verify(authorization.substring(BEARER.length()));
		} catch (Exception e) {
			throw new JwtException("JWT is wrong. " + e.getMessage());
		}
	}
	
	public List<String> roles(String authorization) throws JwtException{
		return Arrays.asList(this.verify(authorization).getClaim(ROLES).asArray(String.class));
	}
}
