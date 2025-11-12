package ch09.v1;

import java.util.List;

public class PasswordVerifier {

    public PasswordVerifier() {
    }

    public boolean verifyPassword(String password, List<String> options, int dayOfWeek) {
        if (dayOfWeek == 0 || dayOfWeek == 6) {
            throw new IllegalArgumentException("It's the weekend!");
        }

        // 비밀번호 길이 검사
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        // 옵션 검사: 대문자 포함 여부
        if (options.contains("uppercase") && !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }

        // 모든 조건 통과 시 true 반환
        return true;
    }
}
