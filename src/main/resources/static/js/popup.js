"use strict";

$(function() {
    $(window).on("load", () => {
        const $addItemCard = $(".add-item-card");

        if($addItemCard.data("flag")){
            $addItemCard.fadeIn().delay(2000).fadeOut();
        }
    })
})