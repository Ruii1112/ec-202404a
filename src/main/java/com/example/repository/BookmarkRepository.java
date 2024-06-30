package com.example.repository;

import com.example.domain.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * bookmarksテーブルを操作するリポジトリ.
 *
 * @author rui.inoue
 */
@Repository
public class BookmarkRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** bookmarksテーブルにある情報をドメインに詰め替えるRowMapper */
    private static final RowMapper<Bookmark> BOOKMARK_ROW_MAPPER = (rs, i) -> {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(rs.getInt("id"));
        bookmark.setUserId(rs.getInt("user_id"));
        bookmark.setItemId(rs.getInt("item_id"));
        return bookmark;
    };

    /**
     * ユーザidと商品idによる検索.
     *
     * @param userId ユーザid
     * @param itemId 商品id
     * @return ブックマーク情報
     */
    public Bookmark findByUserIdAndItemId(Integer userId, Integer itemId){
        String sql = """
                SELECT
                    id, user_id, item_id
                FROM
                    bookmarks
                WHERE
                    user_id=:userId AND item_id=:itemId
                ;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId", itemId);
        List<Bookmark> bookmarkList = template.query(sql, param, BOOKMARK_ROW_MAPPER);
        if(bookmarkList.isEmpty()){
            return null;
        }
        return bookmarkList.get(0);
    }

    /**
     * ブックマークのinsert.
     *
     * @param bookmark ブックマーク情報
     */
    public void insert(Bookmark bookmark){
        SqlParameterSource param = new BeanPropertySqlParameterSource(bookmark);
        String sql = """
                INSERT INTO bookmarks (
                    user_id, item_id
                )VALUES(
                    :userId, :itemId
                );
                """;
        template.update(sql, param);
    }

    /**
     * ブックマーク情報の削除.
     *
     * @param id ブックマーク情報のid
     */
    public void delete(Integer id){
        String sql = """
                DELETE FROM bookmarks
                WHERE
                    id=:id
                ;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
