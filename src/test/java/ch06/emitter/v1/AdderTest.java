package ch06.emitter.v1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ch06.click.ClickExample;
import org.junit.jupiter.api.Test;

public class AdderTest {
    @Test
    void buttonClickTriggersLabelChange() {
        // given: 컴포넌트 준비
        ClickExample example = new ClickExample();

        // document.load() 이벤트 → 초기 상태 검증
        assertThat(example.getResultLabel().getText())
                .isEqualTo("Document Loaded");

        // when: button.click()
        example.getButton().doClick();

        // then: resultDiv.innerText === "Clicked!"
        assertThat(example.getResultLabel().getText())
                .isEqualTo("Clicked!");
    }
}