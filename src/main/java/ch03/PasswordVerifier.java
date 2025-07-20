package ch03;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class PasswordVerifier {

    public List<String> verifyPassword(String input, List<String> rules) {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }

        // rules에 따른 검증은 아직 구현되지 않음
        // 향후 규칙 기반 검증 로직 추가 예정

        return List.of(); // 발견한 오류 리스트
    }
}