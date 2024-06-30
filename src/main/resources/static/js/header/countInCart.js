"use strict";

$(function() {
    const getCartItemCount = () => {
        $.ajax({
            url: "http://localhost:8080/ec-202404a/get-item-info/count-item",
            type: "get",
            dataType: "json",
            async: true,
        }).done((data) => {
            updateCartItemCount(data.countItem);
        }).fail((XMLHttpRequest, textStatus, errorThrown) => {
            console.log(XMLHttpRequest)
            console.log(textStatus)
            console.log(errorThrown)
        })
    }

    const updateCartItemCount = (count) => {
        const $badge = $("#cartItemCount");
        $badge.text(count);
        if(count > 0){
            $badge.show();
        }else{
            $badge.hide();
        }
    }

    getCartItemCount();
})