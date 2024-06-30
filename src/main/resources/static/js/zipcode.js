"use strict";

$(function() {
    $("#searchAddressBtn").on("click", () => {
        $.ajax({
            url: "https://zipcoda.net/api",
            type: "get",
            dataType: "json",
            data: {
                zipcode: $("#destinationZipcode").val()
            },
            async: true
        }).done((data) => {
            $("#destinationPrefecture").val(data.items[0].components[0]);
            $("#destinationMunicipalities").val(data.items[0].components[1]);
            $("#destinationAddress").val(data.items[0].components[2]);
        }).fail((XMLHttpRequest, textStatus, errorThrown) => {
            console.log(XMLHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        })
    })
})