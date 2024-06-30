package com.example.repository;

import com.example.domain.OrderItem;
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
 * order_itemsテーブル操作用のリポジトリ.
 *
 * @author rui.inoue
 */
@Repository
public class OrderItemRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** order_itemsテーブルにある情報をドメインに詰め替えるRowMapper　*/
    private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs, i) -> {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getInt("id"));
        orderItem.setItemId(rs.getInt("item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setSize(rs.getString("size").toCharArray()[0]);
        return orderItem;
    };

    /**
     * 注文商品情報のinsert.
     *
     * @param orderItem 注文商品情報
     * @return idがセットされた注文商品情報
     */
    public OrderItem insert(OrderItem orderItem){
        SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
        String sql = """
                INSERT INTO order_items (
                    item_id,
                    order_id,
                    quantity,
                    size
                ) VALUES (
                    :itemId, :orderId, :quantity, :size
                );
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] keyColumnNames = {"id"};
        template.update(sql, param, keyHolder, keyColumnNames);
        orderItem.setId(keyHolder.getKey().intValue());
        return orderItem;
    }

    /**
     * idに基づくレコードの削除.
     *
     * @param id レコードのid
     */
    public void deleteById(Integer id){
        String sql = """
                DELETE FROM order_items WHERE id=:id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    /**
     * オーダーアイテムのオーダーIDの更新
     *
     * @param orderItem オーダーアイテム
     */
    public void updateOrderItem(OrderItem orderItem){
        SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
        String sql = "UPDATE order_items SET order_id = :orderId WHERE id = :id;";
        template.update(sql, param);
    }
}
