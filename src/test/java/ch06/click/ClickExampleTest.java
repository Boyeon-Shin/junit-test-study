package ch06.click;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import org.junit.jupiter.api.Test;

class ClickExampleTest {

    @Test
    void buttonClickChangesLabelText() {
        // given: 버튼과 라벨 생성 (index.html의 button, div 대응)
        JButton button = new JButton("Click Me!");
        JLabel resultLabel = new JLabel("Waiting...");

        // load 이벤트 시뮬레이션 (JS의 window.onload 대응)
        resultLabel.setText("Document Loaded");

        // 클릭 이벤트 핸들러 등록 (JS의 addEventListener("click", cb) 대응)
        button.addActionListener(e -> resultLabel.setText("Clicked!"));

        // when: 버튼 클릭 이벤트 발생
        button.doClick(); // JS의 button.click()

        // then: 라벨 텍스트가 "Clicked!"로 바뀌었는지 검증
        assertThat(resultLabel.getText()).isEqualTo("Clicked!");
    }

}