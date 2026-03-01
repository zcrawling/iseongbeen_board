package io.iseongbeen.demo.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    private val protectedMethods = setOf("POST", "PUT", "DELETE")

    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain
    ) {
        if (req.method in protectedMethods && req.requestURI.startsWith("/api/") &&
            !req.requestURI.startsWith("/api/auth")) {

            val token = req.getHeader("Authorization")?.removePrefix("Bearer ")
            if (token == null || !jwtUtil.validateToken(token)) {
                res.status = HttpServletResponse.SC_UNAUTHORIZED
                res.writer.write("{\"error\":\"인증이 필요합니다\"}")
                return
            }
        }
        chain.doFilter(req, res)
    }
}
