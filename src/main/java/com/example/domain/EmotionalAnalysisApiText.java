package com.example.domain;

/**
 * 感情分析apiに渡すドメイン.
 *
 * @author rui.inoue
 */
public class EmotionalAnalysisApiText {
    /** テキスト　*/
    private String text;

    @Override
    public String toString() {
        return "EmotionalAnalysisApiText{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
