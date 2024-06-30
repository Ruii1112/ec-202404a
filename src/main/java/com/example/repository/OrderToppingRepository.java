package com.example.repository;

import com.example.domain.OrderTopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * order_toppingsテーブル操作用のリポジトリ.
 *
 * @author rui.inoue
 */
@Repository
public class OrderToppingRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 注文トッピング情報のinsert.
     *
     * @param orderTopping 注文トッピング情報
     */
    public void insert(OrderTopping orderTopping){
        SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
        String sql = """
                INSERT INTO order_toppings (
                    topping_id,
                    order_item_id
                ) VALUES (
                    :toppingId, :orderItemId
                );
                """;
        template.update(sql, param);
    }
}
