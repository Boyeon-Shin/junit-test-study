package ch03;

import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Function;

// 고차 함수 역할
public class PasswordVerifierFactory {

    public static Function<String, List<String>> makeVerifier(List<String> rules, DayProvider dayProvider) {
        return (input) -> {
            DayOfWeek day = dayProvider.getDay();

            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                throw new RuntimeException("It's the weekend!");
            }

            // 향후 규칙 기반 검증 로직 추가 예정
            return List.of();
        };
    }
}