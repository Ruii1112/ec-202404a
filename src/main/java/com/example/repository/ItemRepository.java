package com.example.repository;

import com.example.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品情報一覧を扱うリポジトリ.
 *
 * @author krkrHotaru
 */

@Repository
public class ItemRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** itemsテーブルにある商品情報をドメインに詰め替えるRowMapper */
    private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPriceS(rs.getInt("price_s"));
        item.setPriceM(rs.getInt("price_m"));
        item.setPriceL(rs.getInt("price_l"));
        item.setImagePath(rs.getString("image_path"));
        return item;
    };

    /**
     * 検索ワードと開始位置の如何に沿って、削除フラグのたっていない商品の情報を12件検索します.
     *
     * @return 検索結果に沿った商品情報12件のリスト
     */
    public List<Item> findItemsSearchByWordOrderBySortClipByOffset(String searchWord, String sortStr, Integer offset){
        String sql = """
                        SELECT id,name,description,price_s,price_m,price_l,image_path
                        FROM items
                        WHERE deleted = false
                        """;
        if (searchWord != null) {
            sql += " AND name iLIKE :searchWord";
        }
        if (sortStr != null){
            sql += " ORDER BY " + sortStr;
        }
        sql += " LIMIT 12 ";
        if (offset != null) {
            sql += " OFFSET :offset ";
        }
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("searchWord", "%" + searchWord + "%")
                .addValue("offset", offset);
        return template.query(sql,param,ITEM_ROW_MAPPER);
    }

    /**
     * idに基づく商品の検索.
     *
     * @param id 検索する商品id
     * @return 商品情報
     */
    public Item findById(Integer id){
        String sql = """
                SELECT
                    id, name, description, price_s, price_m, price_l, image_path
                FROM
                    items
                WHERE
                    id=:id
                ;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
        return item;
    }

    /**
     * 渡されたid以外の商品の検索.
     *
     * @param values 検索から除外する商品id
     * @return 商品情報
     */
    public List<Item> findExceptIdValues(List<Integer> values){
        String sql = """
                SELECT
                    id, name, description, price_s, price_m, price_l, image_path
                FROM
                    items
                WHERE
                    id NOT IN (:values)
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("values",values);
        List<Item> itemList = template.query(sql,param, ITEM_ROW_MAPPER);
        return itemList;
    }

    /**
     * 条件に沿って検索された商品情報の全行数を取得.
     *
     * @param searchWord 文字列が入るとあいまい検索結果数カウント、nullが入ると全件検索の検索結果数カウント
     * @return 検索結果の行数
     */
    public Integer countItemRows(String searchWord) {
        String sql = """
                SELECT count(*) FROM items
                WHERE name iLIKE :searchWord;
                """;
        if (searchWord == null) {
            searchWord = "";
        }
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("searchWord", "%" + searchWord + "%");
        return template.queryForObject(sql, param, Integer.class);
    }
}
