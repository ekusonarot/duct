# ductの練習

## ルーティングの追加

*hello/resources/hello/config.edn

```
 {:duct.core/project-ns hello

  :duct.router/ataraxy
  {:routes {[:get "/example"] [:hello.handler/example]
            [:get "/todo"] [:hello.handler/todo]}};ルーティングの追加;"/todo"にGETリクエストが来たとき:hello.handler/todoで処理する
  
  
  :hello.handler/example {}
  :hello.handler/todo {}};:hello.handler.todoコンポーネントの初期化時にからのマップを渡すことを示す
```

# ハンドラの実装

integrantでマルチメソッドとして定義されている，ig/init-keyを実装することによりコンポーネントを作成

*hello/src/hello/handler/todo.clj

```
(defmethod ig/init-key :hello.handler/todo [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok {:todo "data"}]))
```

# パラメータの取得

HTTPリクエスト情報はマップで渡される

```
(defmethod ig/init-key :hello.handler/example [_ option]
  (fn [{[_ params] :ataraxy/result}]
    ;処理
    )
```
