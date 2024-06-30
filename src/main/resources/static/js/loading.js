"use strict";

$(function() {
    const showLoader = () => {
        $(".spinner-box").css("display", "flex");
    }

    const hideLoader = () => {
        $(".spinner-box").css("display", "none");
    }

    showLoader();

    $(window).on("load", () => {
        setTimeout(() => {
            hideLoader();
        }, 1000);
    })
})