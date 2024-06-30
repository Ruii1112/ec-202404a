'use strict'

$('#zipcode').on('keyup', () => {
  const zipcode = $('#zipcode').val()
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
      $('#prefecture').val(data.items[0].components[0])
      $('#prefecture').next().addClass('active')
      $('#prefecture').addClass('valid')
      $('#municipalities').val(data.items[0].components[1])
      $('#municipalities').next().addClass('active')
      $('#municipalities').addClass('valid')
      $('#address').val(data.items[0].components[2])
      $('#address').next().addClass('active')
      $('#address').addClass('valid')
    })
    .fail((XMLHttpRequest, textStatus, errorThrown) => {
      console.log(XMLHttpRequest)
      console.log(textStatus)
      console.log(errorThrown)
    })
})
