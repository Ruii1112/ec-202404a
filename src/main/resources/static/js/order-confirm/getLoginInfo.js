'use strict'

$(function () {
  $('#loginInfoBtn').on('click', () => {
    $.ajax({
      url: 'http://localhost:8080/ec-202404a/get-user/user-info',
      type: 'get',
      dataType: 'json',
      async: true,
    })
      .done((data) => {
        $('#prefecture').next().addClass('active')
        $('#prefecture').addClass('valid')
        $('#destinationName').val(data.user.name)
        $('#destinationName').next().addClass('active')
        $('#destinationName').addClass('valid')
        $('#destinationEmail').val(data.user.email)
        $('#destinationEmail').next().addClass('active')
        $('#destinationEmail').addClass('valid')
        $('#destinationZipcode').val(data.user.zipcode)
        $('#destinationZipcode').next().addClass('active')
        $('#destinationZipcode').addClass('valid')
        $('#destinationPrefecture').val(data.user.prefecture)
        $('#destinationPrefecture').next().addClass('active')
        $('#destinationPrefecture').addClass('valid')
        $('#destinationMunicipalities').val(data.user.municipalities)
        $('#destinationMunicipalities').next().addClass('active')
        $('#destinationMunicipalities').addClass('valid')
        $('#destinationAddress').val(data.user.address)
        $('#destinationAddress').next().addClass('active')
        $('#destinationAddress').addClass('valid')
        $('#destinationTel').val(data.user.telephone)
        $('#destinationTel').next().addClass('active')
        $('#destinationTel').addClass('valid')
      })
      .fail((XMLHttpRequest, textStatus, errorThrown) => {
        console.log(XMLHttpRequest)
        console.log(textStatus)
        console.log(errorThrown)
      })
  })
})
