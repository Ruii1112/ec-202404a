package com.example.repository;

import com.example.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * toppingsテーブルを操作するリポジトリ.
 *
 * @author rui.inoue
 */
@Repository
public class ToppingRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** toppingsテーブルにある情報をドメインに詰め替えるRowMapper */
    private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
        Topping topping = new Topping();
        topping.setId(rs.getInt("id"));
        topping.setName(rs.getString("name"));
        topping.setPrice(rs.getInt("price"));
        return topping;
    };

    /**
     * トッピングの全件検索.
     *
     * @return 全トッピング情報のリスト
     */
    public List<Topping> findAll(){
        String sql = """
                SELECT
                    id, name, price
                FROM
                    toppings
                """;
        List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
        return toppingList;
    }
}
