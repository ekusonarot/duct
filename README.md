# ductの練習

## ルーティングの追加

*crud/resources/crud/config.edn

```
:duct.profile/base
 {:duct.core/project-ns crud
 
  :duct.router/ataraxy
  {:routes {[:get "/example"] [:hello.handler/example]
            [:get "/todo"] [:hello.handler.todo/list]}} ;ルーティングの追加;"/todo"にGETリクエストが来たとき:crud.handler.todos/listで処理する
  
  
  :crud.handler/example ;依存関係
  {:db #ig/ref :duct.database/sql}
  :crud.handler.todo/list
  {:db #ig/ref :duct.database/sql}};:hello.handler.todoコンポーネントの初期化時にからのマップを渡すことを示す
  ...
  }
```

## マイグレーション設定

システム起動時にテーブルの作成などを行う．

*crud/resources/crud/config.edn

```
:duct.profile/base
{:duct.core/project-ns crud
  ...
  :duct.migrator/ragtime ;マイグレーション設定
  {:database #ig/ref :duct.database/sql
   :strategy :rebase
   :migrations [#ig/ref :crud.migration/create-crud-table]}

  [:duct.migrator.ragtime/sql :crud.migration/create-crud-table]
  {:up ["CREATE TABLE todos (id SERIAL PRIMARY KEY, title TEXT);"
        "INSERT INTO todos (title) values('test1');"
        "INSERT INTO todos (title) values('test2');"]
   :down ["DROP TABLE todos;"]}}
```

## ハンドラの実装

integrantでマルチメソッドとして定義されている，ig/init-keyを実装することによりコンポーネントを作成

*hello/src/hello/handler/todo.clj

```
(defmethod ig/init-key :hello.handler/todo [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok {:todo "data"}]))
```

## パラメータの取得

HTTPリクエスト情報はマップで渡される

```
(defmethod ig/init-key :hello.handler/example [_ option]
  (fn [{[_ params] :ataraxy/result}]
    ;処理
    )
```
