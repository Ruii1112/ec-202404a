'use strict'

const autoConfig = {
  placeHolder: '商品検索',
  selector: '#searchInput',
  data: {
    src: async () => {
      try {
        const formData = new FormData()
        formData.append('input', $('#searchInput').val())
        const source = await fetch(
          `http://localhost:8080/ec-202404a/get-item-info`,
          {
            method: 'post',
            cache: 'no-cache',
            body: formData,
          }
        )
        const data = await source.json()
        return data.itemNameList
      } catch (error) {
        return error
      }
    },
  },
  resultItem: {
    highlight: true,
  },
  events: {
    list: {
      click: (e) => {
        console.log(e.target.innerText)
        $('#searchInput').val(e.target.innerText)
        $('#searchForm').submit()
      },
    },
  },
  resultsList: {
    maxResults: 12,
  },
  submit: true,
}
const autoCompleteJS = new autoComplete(autoConfig)
