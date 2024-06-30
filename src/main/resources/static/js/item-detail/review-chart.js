'use strict'

const ctx = document.getElementById('myChart')

const [reviewListValues, mostPositiveReview, mostNegativeReview] = ((arr) => {
  const sum = arr.slice(0, 3).reduce((acc, v) => acc + v)
  return [
    arr.map((v) => Math.round((v / sum) * 10000) / 100),
    ...arr.slice(3).map((v) => [Math.round(v[0] * 10000) / 100, v[1]]),
  ]
})(
  reviewList.reduce(
    (
      [positiveValue, negativeValue, neutralValue, mostPositive, mostNegative],
      v
    ) => [
      positiveValue + v.positiveValue,
      negativeValue + v.negativeValue,
      neutralValue + v.neutralValue,
      mostPositive[0] > v.positiveValue
        ? mostPositive
        : [v.positiveValue, v.content],
      mostNegative[0] > v.negativeValue
        ? mostNegative
        : [v.negativeValue, v.content],
    ],
    [0, 0, 0, [0, ''], [0, '']]
  )
)

$('#value--positive').text(reviewListValues[0])
$('#value--negative').text(reviewListValues[1])
$('#value--neutral').text(reviewListValues[2])

if (mostPositiveReview[0] > 30) {
  $('#positive-review__value').text(mostPositiveReview[0])
  $('#positive-review').text(mostPositiveReview[1])
} else {
  $('#positive-review__value').text('---')
}
if (mostNegativeReview[0] > 30) {
  $('#negative-review__value').text(mostNegativeReview[0])
  $('#negative-review').text(mostNegativeReview[1])
} else {
  $('#negative-review__value').text('---')
}

const data = {
  labels: ['ポジティブ', 'ネガティブ', 'ニュートラル'],
  datasets: [
    {
      data: reviewListValues,
      backgroundColor: ['#00e676', '#f44336', 'rgb(255, 205, 86)'],
      borderColor: 'rgb(34, 34, 34)',
      hoverOffset: 4,
    },
  ],
}

const options = {
  plugins: {
    legend: {
      display: false,
    },
  },
}

const config = {
  type: 'pie',
  data,
  options,
}
new Chart(ctx, config)
