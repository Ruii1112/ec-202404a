"use strict";

$(function() {
    if(!$(".credit-method").prop("checked")){
        $(".credit-info").hide();
    }

    $(".money-method, .credit-method").on("click", (e) => {
        if($(e.target).val() == 1){
            $(".credit-info").hide()
        }else if($(e.target).val() == 2){
            $(".credit-info").slideDown();
        }
    })
})