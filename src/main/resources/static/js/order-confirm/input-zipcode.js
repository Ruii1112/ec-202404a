'use strict'

$('#destinationZipcode').on('keyup', () => {
  const zipcode = $('#destinationZipcode').val()
  if (zipcode.replace('-', '').length < 7) return
  $.ajax({
    url: 'https://zipcoda.net/api',
    type: 'get',
    dataType: 'json',
    data: {
      zipcode,
    },
    async: true,
  })
    .done((data) => {
      $('#destinationPrefecture').val(data.items[0].components[0])
      $('#destinationPrefecture').next().addClass('active')
      $('#destinationPrefecture').addClass('valid')
      $('#destinationMunicipalities').val(data.items[0].components[1])
      $('#destinationMunicipalities').next().addClass('active')
      $('#destinationMunicipalities').addClass('valid')
      $('#destinationAddress').val(data.items[0].components[2])
      $('#destinationAddress').next().addClass('active')
      $('#destinationAddress').addClass('valid')
    })
    .fail((XMLHttpRequest, textStatus, errorThrown) => {
      console.log(XMLHttpRequest)
      console.log(textStatus)
      console.log(errorThrown)
    })
})
