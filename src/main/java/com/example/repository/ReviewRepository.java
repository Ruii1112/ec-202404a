package com.example.repository;

import com.example.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * reviewsテーブル操作用のリポジトリ.
 *
 * @author rui.inoue
 */
@Repository
public class ReviewRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** reviewsテーブルにある情報をドメインに詰め替えるRowMapper */
    private static final RowMapper<Review> REVIEW_ROW_MAPPER = (rs, i) -> {
        Review review = new Review();
        review.setId(rs.getInt("id"));
        review.setContent(rs.getString("content"));
        review.setUserId(rs.getInt("user_id"));
        review.setItemId(rs.getInt("item_id"));
        review.setPositiveValue(rs.getDouble("positive_value"));
        review.setNegativeValue(rs.getDouble("negative_value"));
        review.setNeutralValue(rs.getDouble("neutral_value"));
        return review;
    };

    /**
     * 商品idに基づくレビューの検索.
     *
     * @param itemId 商品id
     * @return レビュー情報のリスト
     */
    public List<Review> findByItemId(Integer itemId){
        String sql = """
                SELECT
                    id, content, user_id, item_id, positive_value, negative_value, neutral_value
                FROM
                    reviews
                WHERE
                    item_id=:itemId
                ;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
        List<Review> reviewList = template.query(sql, param, REVIEW_ROW_MAPPER);
        return reviewList;
    }

    /**
     * レビューのinsert.
     *
     * @param review レビュー情報
     * @return idをセットしたレビュー情報
     */
    public Review insert(Review review){
        SqlParameterSource param = new BeanPropertySqlParameterSource(review);
        String sql = """
                INSERT INTO reviews (
                    content,
                    user_id,
                    item_id,
                    positive_value,
                    negative_value,
                    neutral_value
                ) VALUES (
                    :content, :userId, :itemId, :positiveValue, :negativeValue, :neutralValue
                );
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] keyColumnNames = {"id"};
        template.update(sql, param, keyHolder, keyColumnNames);
        review.setId(keyHolder.getKey().intValue());
        return review;
    }
}
