package com.example.service;

import com.example.domain.EmotionalAnalysisApiText;
import com.example.domain.Review;
import com.example.repository.ReviewRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * レビュー機能の処理をするサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Value("${emotional-analysis-api}")
    private String emotionalAnalysisApiUrl;

    /**
     * レビューの保存.
     *
     * @param review レビュー情報
     * @param userId ユーザid
     */
    public void addReview(Review review, Integer userId){
        review.setUserId(userId);

        RestTemplate restTemplate = new RestTemplate();
        EmotionalAnalysisApiText text = new EmotionalAnalysisApiText();
        text.setText(review.getContent());
        JsonNode jsonNode = restTemplate.postForObject(emotionalAnalysisApiUrl, text, JsonNode.class);

        if(jsonNode.findValue("ResponseMetadata").findValue("HTTPStatusCode").asInt() == 200){
            review.setNegativeValue(jsonNode.findValue("SentimentScore").findValue("Negative").asDouble());
            review.setPositiveValue(jsonNode.findValue("SentimentScore").findValue("Positive").asDouble());
            review.setNeutralValue(jsonNode.findValue("SentimentScore").findValue("Neutral").asDouble());
            review = reviewRepository.insert(review);
        }
    }
}
