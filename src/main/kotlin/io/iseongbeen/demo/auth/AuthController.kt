package io.iseongbeen.demo.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class LoginRequest(val password: String)
data class LoginResponse(val token: String)

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val jwtUtil: JwtUtil,
    @Value("\${admin.password}") private val adminPassword: String
) {
    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<Any> {
        if (req.password != adminPassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(mapOf("error" to "비밀번호가 틀렸습니다"))
        }
        return ResponseEntity.ok(LoginResponse(jwtUtil.generateToken()))
    }
}
