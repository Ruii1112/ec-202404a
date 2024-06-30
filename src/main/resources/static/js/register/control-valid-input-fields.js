'use strict'

$('input[type!=hidden]:not(:has(~ span.helper-text))').each((_, inputField) => {
  $(inputField).val() !== '' && $(inputField).addClass('valid')
})

$('input[type!=hidden]:has(~ span.helper-text)').each((_, inputField) => {
  $(inputField).addClass('invalid')
})
