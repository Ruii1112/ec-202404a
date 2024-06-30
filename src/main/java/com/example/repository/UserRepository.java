package com.example.repository;

import com.example.domain.Item;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * usersテーブルを操作するリポジトリ.
 *
 * @author YusakuTerashima
 */
@Repository
public class UserRepository {
    /**
     * ユーザ情報登録用のRowMapper.
     */
    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setZipcode(rs.getString("zipcode"));
        user.setPrefecture(rs.getString("prefecture"));
        user.setMunicipalities(rs.getString("municipalities"));
        user.setAddress(rs.getString("address"));
        user.setTelephone(rs.getString("telephone"));
        user.setAdminFlag(rs.getBoolean("admin_flag"));
        return user;
    };

    /** ユーザ情報をすべてドメインに入れるResultSetExtractor */
    private static final ResultSetExtractor<List<User>> USER_RESULT_SET_EXTRACTOR = (rs) -> {
        List<User> userList = new ArrayList<>();
        List<Item> itemList = null;
        Integer formerId = -1;

        while (rs.next()){
            if(formerId != rs.getInt("u_id")){
                User user = new User();
                userList.add(user);
                user.setId(rs.getInt("u_id"));
                user.setName(rs.getString("u_name"));
                user.setEmail(rs.getString("u_email"));
                user.setPassword(rs.getString("u_password"));
                user.setZipcode(rs.getString("u_zipcode"));
                user.setPrefecture(rs.getString("u_prefecture"));
                user.setMunicipalities(rs.getString("u_municipalities"));
                user.setAddress(rs.getString("u_address"));
                user.setTelephone(rs.getString("u_telephone"));
                user.setAdminFlag(rs.getBoolean("u_admin_flag"));
                itemList = new ArrayList<>();
                user.setBookmarkList(itemList);
                formerId = user.getId();
            }
            if(rs.getObject("i_id") != null){
                Item item = new Item();
                item.setId(rs.getInt("i_id"));
                item.setName(rs.getString("i_name"));
                item.setDescription(rs.getString("i_description"));
                item.setPriceS(rs.getInt("i_price_s"));
                item.setPriceM(rs.getInt("i_price_m"));
                item.setPriceL(rs.getInt("i_price_l"));
                item.setImagePath(rs.getString("i_image_path"));
                itemList.add(item);
            }
        }
        return userList;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * ユーザを登録する.
     *
     * @param user 登録するユーザ
     */
    public void insert(User user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = "INSERT INTO users(name,email,password,zipcode,prefecture,municipalities,address,telephone)VALUES(:name,:email,:password,:zipcode,:prefecture,:municipalities,:address,:telephone);";
        template.update(sql, param);
    }

    /**
     * ユーザ情報の更新.
     *
     * @param user ユーザ情報
     */
    public void update(User user){
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = """
                    UPDATE users
                    SET
                      name=:name,
                      email=:email,
                      zipcode=:zipcode,
                      prefecture=:prefecture,
                      municipalities=:municipalities,
                      address=:address,
                      telephone=:telephone,
                      deleted_at=:deletedAt
                    WHERE id=:id;
                    """;
        template.update(sql,param);
    }

    /**
     * テーブル中で一意のユーザ情報を受け取り、それに一致したユーザを返します.
     * この時、削除済みのユーザは検索結果に含みません。
     * IDとメールアドレスはどちらも一意なので、両方を渡した際はそのユーザ以外でメールアドレスの重複があれば返します。
     *
     * @param user ユーザ情報
     * @return 検索条件に一致した一意のユーザ情報
     */
    public User findByUniqueUserAttribute(User user){
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = """
                SELECT
                 	u.id AS u_id,
                 	u.name AS u_name,
                 	u.email AS u_email,
                 	u.password AS u_password,
                 	u.zipcode AS u_zipcode,
                 	u.prefecture AS u_prefecture,
                 	u.municipalities AS u_municipalities,
                 	u.address AS u_address,
                 	u.telephone AS u_telephone,
                 	u.admin_flag AS u_admin_flag,
                 	i.id AS i_id,
                 	i.name AS i_name,
                 	i.description AS i_description,
                 	i.price_s AS i_price_s,
                 	i.price_m AS i_price_m,
                 	i.price_l AS i_price_l,
                 	i.image_path AS i_image_path
                 FROM
                 	users AS u
                 LEFT OUTER JOIN
                 	bookmarks AS b
                 ON
                 	u.id=b.user_id
                 LEFT OUTER JOIN
                 	items AS i
                 ON
                 	b.item_id=i.id
                """;
        if (user.getId() != null && user.getEmail() != null){
            sql += " WHERE u.id<>:id AND u.email=:email ";
        } else if (user.getId() != null) {
            sql += " WHERE u.id=:id ";
        } else if (user.getEmail() != null) {
            sql += " WHERE u.email=:email ";
        }
        sql += " AND u.deleted_at IS NULL ";
        List<User> results = template.query(sql, param, USER_RESULT_SET_EXTRACTOR);
        if (results.isEmpty()){
            return null;
        }
        return results.get(0);
    }
}