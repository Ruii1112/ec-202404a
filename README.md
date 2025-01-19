# 楽楽製麺ECサイト

## 注意点
* `src/main/resources/application.yml`を作成しないと動きません。
  * DB情報
  * メール情報
  * springdocの出力先
  ```dtd
  springdoc:
    api-docs:
        path: /docs/api-docs
    swagger-ui:
        path: /docs/swagger-ui.html
  ```
  * クレジットカードバリデーションの外部API情報
  * 感情分析の外部API情報