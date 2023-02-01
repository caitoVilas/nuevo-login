package com.caito.usuarios.security.jwt;

import com.caito.usuarios.security.UsuarioPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
   // private Integer exp = Integer.parseInt(expiration);
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);



    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .signWith(this.getKey(secret))
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("roles", getRoles(usuarioPrincipal))
                //.claim("email", usuarioPrincipal.getEmail())
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("token vencido");
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (SignatureException e){
            logger.error("error en la firma del token");
        }catch (IllegalArgumentException e){

        }
        return false;
    }

    private List<String> getRoles(UsuarioPrincipal usuarioPrincipal){
        return usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private Key getKey(String secret){
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
