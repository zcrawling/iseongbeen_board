package io.iseongbeen.demo.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil(
    @Value("\${admin.jwt-secret}") private val secret: String
) {
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(): String = Jwts.builder()
        .subject("admin")
        .issuedAt(Date())
        .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24시간
        .signWith(key)
        .compact()

    fun validateToken(token: String): Boolean = runCatching {
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
        true
    }.getOrDefault(false)
}
