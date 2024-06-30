package com.example.repository;

import com.example.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * ordersテーブルを操作するリポジトリ
 *
 * @author rui.inoue
 */
@Repository
public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /** ordersテーブルにある情報をドメインに詰め替えるRowMapper */
    @Autowired
    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("user_id"));
        order.setStatus(rs.getInt("status"));
        order.setTotalPrice(rs.getInt("total_price"));
        order.setDestinationName(rs.getString("destination_name"));
        order.setDestinationEmail(rs.getString("destination_email"));
        order.setDestinationZipcode(rs.getString("destination_zipcode"));
        order.setDestinationPrefecture(rs.getString("destination_prefecture"));
        order.setDestinationMunicipalities(rs.getString("destination_municipalities"));
        order.setDestinationAddress(rs.getString("destination_address"));
        order.setDestinationTel(rs.getString("destination_tel"));
        if(rs.getTimestamp("order_time") != null) {
            order.setOrderTime(rs.getTimestamp("order_time").toLocalDateTime());
        }
        if(rs.getTimestamp("delivery_time") != null) {
            order.setDeliveryTime(rs.getTimestamp("delivery_time").toLocalDateTime());
        }
        order.setPaymentMethod(rs.getInt("payment_method"));
        return order;
    };

    /** order情報すべてのプロパティのセット */
    private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
        Order order = new Order();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        List<OrderItem> orderItemList = null;
        List<OrderTopping> orderToppingList = null;
        int formerId = -1;

        while (rs.next()){
            if(formerId == -1){
                order.setId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setStatus(rs.getInt("status"));
                order.setTotalPrice(rs.getInt("total_price"));
                order.setDestinationName(rs.getString("destination_name"));
                order.setDestinationEmail(rs.getString("destination_email"));
                order.setDestinationZipcode(rs.getString("destination_zipcode"));
                order.setDestinationPrefecture(rs.getString("destination_prefecture"));
                order.setDestinationMunicipalities(rs.getString("destination_municipalities"));
                order.setDestinationAddress(rs.getString("destination_address"));
                order.setDestinationTel(rs.getString("destination_tel"));
                if(rs.getTimestamp("order_time") != null) {
                    order.setOrderTime(rs.getTimestamp("order_time").toLocalDateTime());
                }
                if(rs.getTimestamp("delivery_time") != null) {
                    order.setDeliveryTime(rs.getTimestamp("delivery_time").toLocalDateTime());
                }
                order.setPaymentMethod(rs.getInt("payment_method"));
                orderItemList = new ArrayList<>();
                order.setOrderItemList(orderItemList);
            }
            if(formerId != rs.getInt("order_item_id") && rs.getInt("order_item_id") != 0){
                formerId = rs.getInt("order_item_id");
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("order_item_id"));
                orderItem.setItemId(rs.getInt("item_id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                if(rs.getString("size") != null) {
                    orderItem.setSize(rs.getString("size").toCharArray()[0]);
                }
                Item item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("item_name"));
                item.setDescription(rs.getString("item_description"));
                item.setPriceS(rs.getInt("price_s"));
                item.setPriceM(rs.getInt("price_m"));
                item.setPriceL(rs.getInt("price_l"));
                item.setImagePath(rs.getString("image_path"));
                orderItem.setItem(item);
                orderItemList.add(orderItem);
                orderToppingList = new ArrayList<>();
                orderItem.setOrderToppingList(orderToppingList);
            }
            if(rs.getInt("order_topping_id") != 0) {
                OrderTopping orderTopping = new OrderTopping();
                orderTopping.setId(rs.getInt("order_topping_id"));
                orderTopping.setToppingId(rs.getInt("topping_id"));
                orderTopping.setOrderItemId(rs.getInt("order_item_id"));
                Topping topping = new Topping();
                topping.setId(rs.getInt("topping_id"));
                topping.setName(rs.getString("topping_name"));
                topping.setPrice(rs.getInt("topping_price"));
                orderTopping.setTopping(topping);
                orderToppingList.add(orderTopping);
            }
        }
        return orderList;
    };

    /**
     * ユーザidとstatusに基づく注文情報の検索.
     *
     * @param userId ユーザid
     * @param status 注文状況
     * @return 注文情報
     */
    public Order findByUserIdAndStatus(Integer userId, Integer status){
        String sql = """
                SELECT
                    id, user_id, status, total_price, destination_name, destination_email, destination_zipcode, destination_prefecture, destination_municipalities, destination_address, destination_tel, order_time, delivery_time, payment_method
                FROM
                    orders
                WHERE
                    user_id=:userId AND status=:status
                ;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
        List<Order> orderList = template.query(sql, param, ORDER_ROW_MAPPER);
        if(orderList.isEmpty()){
            return null;
        }
        return orderList.get(0);
    }

    /**
     * 注文情報のinsert.
     *
     * @param order 注文情報
     * @return idをセットした注文情報
     */
    public Order insert(Order order){
        SqlParameterSource param = new BeanPropertySqlParameterSource(order);
        String sql = """
                INSERT INTO orders (
                    user_id,
                    status,
                    total_price,
                    destination_name,
                    destination_email,
                    destination_zipcode,
                    destination_prefecture,
                    destination_municipalities,
                    destination_address,
                    destination_tel,
                    order_time,
                    delivery_time,
                    payment_method
                ) VALUES (
                    :userId, :status, :totalPrice, :destinationName, :destinationEmail, :destinationZipcode, :destinationPrefecture, :destinationMunicipalities, :destinationAddress, :destinationTel, :orderTime, :deliveryTime, :paymentMethod
                );
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] keyColumnNames = {"id"};
        template.update(sql, param, keyHolder, keyColumnNames);
        order.setId(keyHolder.getKey().intValue());
        return order;
    }

    /**
     * ユーザidと注文状況を元に1つの注文情報の全てを取得.
     *
     * @param userId ユーザid
     * @param status 注文状況
     * @return 注文情報
     */
    public Order findAllOrderInfoByUserIdAndStatus(Integer userId, Integer status){
        String sql = """
                SELECT
                	o.id AS order_id,
                	o.user_id,
                	o.status,
                	o.total_price,
                	o.destination_name,
                	o.destination_email,
                	o.destination_zipcode,
                	o.destination_prefecture,
                	o.destination_municipalities,
                	o.destination_address,
                	o.destination_tel,
                	o.order_time,
                	o.delivery_time,
                	o.payment_method,
                	i.id AS item_id,
                	i.name AS item_name,
                	i.description AS item_description,
                	i.price_s,
                	i.price_m,
                	i.price_l,
                	i.image_path,
                	oi.id AS order_item_id,
                	oi.quantity,
                	oi.size,
                	ot.id AS order_topping_id,
                	ot.topping_id,
                	t.name AS topping_name,
                	t.price AS topping_price
                FROM
                	orders o
                LEFT OUTER JOIN
                    order_items oi ON o.id = oi.order_id
                LEFT OUTER JOIN
                	items i ON oi.item_id = i.id
                LEFT OUTER JOIN
                    order_toppings ot ON oi.id = ot.order_item_id
                LEFT OUTER JOIN
                    toppings t ON ot.topping_id = t.id
                WHERE
                    o.user_id = :userId AND o.status = :status
                ORDER BY
                    oi.id DESC
                ;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
        List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
        if(orderList.get(0).getId() == null){
            return null;
        }

        return orderList.get(0);
    }

    /**
     * 主キー検索.
     *
     * @param id 主キー
     * @return 注文情報
     */
    public Order findById(Integer id){
        String sql = """
                SELECT
                	o.id AS order_id,
                	o.user_id,
                	o.status,
                	o.total_price,
                	o.destination_name,
                	o.destination_email,
                	o.destination_zipcode,
                	o.destination_prefecture,
                	o.destination_municipalities,
                	o.destination_address,
                	o.destination_tel,
                	o.order_time,
                	o.delivery_time,
                	o.payment_method,
                	i.id AS item_id,
                	i.name AS item_name,
                	i.description AS item_description,
                	i.price_s,
                	i.price_m,
                	i.price_l,
                	i.image_path,
                	oi.id AS order_item_id,
                	oi.quantity,
                	oi.size,
                	ot.id AS order_topping_id,
                	ot.topping_id,
                	t.name AS topping_name,
                	t.price AS topping_price
                FROM
                	orders o
                LEFT OUTER JOIN
                    order_items oi ON o.id = oi.order_id
                LEFT OUTER JOIN
                	items i ON oi.item_id = i.id
                LEFT OUTER JOIN
                    order_toppings ot ON oi.id = ot.order_item_id
                LEFT OUTER JOIN
                    toppings t ON ot.topping_id = t.id
                WHERE
                    o.id=:id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
        if(orderList.get(0).getId() == null){
            return null;
        }
        return orderList.get(0);
    }

    /**
     * 注文情報の更新.
     *
     * @param order 注文情報
     */
    public void update(Order order){
        SqlParameterSource param = new BeanPropertySqlParameterSource(order);
        String sql = """
                UPDATE orders
                    SET
                    user_id = :userId,
                    status = :status,
                    total_price = :totalPrice,
                    destination_name = :destinationName,
                    destination_email = :destinationEmail,
                    destination_zipcode = :destinationZipcode,
                    destination_prefecture = :destinationPrefecture,
                    destination_municipalities = :destinationMunicipalities,
                    destination_address = :destinationAddress,
                    destination_tel = :destinationTel,
                    order_time = :orderTime,
                    delivery_time = :deliveryTime,
                    payment_method = :paymentMethod
                WHERE
                    id = :id;
                """;
        template.update(sql, param);
    }

    /**
     * ユーザidに基づく注文情報全ての検索.
     *
     * @param userId ユーザid
     * @return 注文情報のリスト
     */
    public List<Order> findByUserId(Integer userId){
        String sql = """
                SELECT
                	o.id AS order_id,
                	o.user_id,
                	o.status,
                	o.total_price,
                	o.destination_name,
                	o.destination_email,
                	o.destination_zipcode,
                	o.destination_prefecture,
                	o.destination_municipalities,
                	o.destination_address,
                	o.destination_tel,
                	o.order_time,
                	o.delivery_time,
                	o.payment_method,
                	i.id AS item_id,
                	i.name AS item_name,
                	i.description AS item_description,
                	i.price_s,
                	i.price_m,
                	i.price_l,
                	i.image_path,
                	oi.id AS order_item_id,
                	oi.quantity,
                	oi.size,
                	ot.id AS order_topping_id,
                	ot.topping_id,
                	t.name AS topping_name,
                	t.price AS topping_price
                FROM
                	orders o
                LEFT OUTER JOIN
                    order_items oi ON o.id = oi.order_id
                LEFT OUTER JOIN
                	items i ON oi.item_id = i.id
                LEFT OUTER JOIN
                    order_toppings ot ON oi.id = ot.order_item_id
                LEFT OUTER JOIN
                    toppings t ON ot.topping_id = t.id
                WHERE
                    o.user_id=:userId;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
        return orderList;
    }

    /**
     * IDに基づいたオーダーを削除.
     *
     * @param id 削除するオーダーID
     */
    public void deleteById(Integer id){
        String sql = """
                DELETE FROM orders WHERE id=:id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    /**
     * ユーザidと複数ステータスによる検索.
     *
     * @param userId ユーザid
     * @param statuses ステータスの配列
     * @return 注文情報のリスト
     */
    public List<Order> findByUserIdAndStatuses(Integer userId, Integer[] statuses){
        String sql = """
                SELECT
                	o.id AS order_id,
                	o.user_id,
                	o.status,
                	o.total_price,
                	o.destination_name,
                	o.destination_email,
                	o.destination_zipcode,
                	o.destination_prefecture,
                	o.destination_municipalities,
                	o.destination_address,
                	o.destination_tel,
                	o.order_time,
                	o.delivery_time,
                	o.payment_method,
                	i.id AS item_id,
                	i.name AS item_name,
                	i.description AS item_description,
                	i.price_s,
                	i.price_m,
                	i.price_l,
                	i.image_path,
                	oi.id AS order_item_id,
                	oi.quantity,
                	oi.size,
                	ot.id AS order_topping_id,
                	ot.topping_id,
                	t.name AS topping_name,
                	t.price AS topping_price
                FROM
                	orders o
                LEFT OUTER JOIN
                    order_items oi ON o.id = oi.order_id
                LEFT OUTER JOIN
                	items i ON oi.item_id = i.id
                LEFT OUTER JOIN
                    order_toppings ot ON oi.id = ot.order_item_id
                LEFT OUTER JOIN
                    toppings t ON ot.topping_id = t.id
                WHERE
                    o.user_id=:userId AND o.status in (:status0
                """;
        for (int i = 1; i < statuses.length; i++) {
            sql += ", :status" + i;
        }
        sql += ")";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        for (int i = 0; i < statuses.length; i++) {
            param.addValue("status" + i, statuses[i]);
        }
        List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
        return orderList;
    }
}
