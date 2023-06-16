package com.insearchofknowledge.bugTracker.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

/* Json Web Token = a compact URL representing claims to be transferred between 2 parties
 * Claims are encoded as JSON object and is digitally signed using JSON web signature
 * 3 types of claims: Registered, Public, Private
 * JWT has 3 parts:
 *    HEADER: "type": "JWT", "alg": "HS256" (signing algorithm)
 *    PAYLOAD: contains the claims (statements about the entity /user and additional date)
 *    SIGNATURE: used to verify the authenticity of the sender and that the message was not tempered with
 *
 *  SigningKey (SECRET KEY )= used to create the signature part of JWT  / it is used in conjunction
 *  with the signing algorithm specified in the header of JWT to create the SIGNATURE
 */

@Service
public class JwtService {

    private static final String SECRET_KEY = "a35441b489fd39cfea2f468592bd8a56c1193e69a0553dbc37b594102608f38a";

//    private static final String CONFIG_FILE_PATH = "/src/main/resources/config.properties";
//    private static final String secretKey = loadSecretKey();
//
//    private static String loadSecretKey() {
//        Properties property = new Properties();
//        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
//            property.load(input);
//            String secretKey = property.getProperty("secretKey");
//            return secretKey;
//        } catch (IOException e){
//            throw new RuntimeException("Properties file or file path issue");
//        }
//    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject); // the subject is usually the Username / email
    }

    // extract needed date from token (expiration date for example)
    // we provide as params the token and the function telling which claim to extract for example: (token, Claims :: getExpiration())
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // generate token without extra claims only with data from userDetails
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails); // we call the 2 param method, giving an empty Map as param
    }

    // generate a token with extra claims; the map will contain the claims
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) // when was the token created; needed to calculate expiration date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // valid for 24 hrs and 1000 milliseconds
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // we sign the token with the secret key + signature algorithm
                .compact();
    }

    // check if token is valid; we compare the username from token subject to the username from userDetails
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // check if token is expired
    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date()); // new Date(); is initialized to the current date
    }

    // get expiration date from token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // We extract claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                // signature part of the JWT which is used to verify that the sender of the jwt is who it claims to be,
                // and it ensures that the message was not tempered with along its path
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
//        loadSecretKey();
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
