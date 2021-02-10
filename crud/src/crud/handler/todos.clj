(ns crud.handler.todos
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [integrant.core :as ig]
            [crud.boundary.todos :as todos]))

(defmethod ig/init-key ::list [_ {:keys [db]}]
  (fn [{[_] :ataraxy/result}]
    (let [todos (todos/get-todos db)]
      [::response/ok todos])))

(defmethod ig/init-key ::create [_ {:keys [db]}]
  (fn [{[_ params] :ataraxy/result}]
    (let [result (todos/create-todo db params)
          id (:id (first result))]
      [::response/created (str "/todos/" id)])))

(defmethod ig/init-key ::delete [_ {:keys [db]}]
  (fn [{[_ id] :ataraxy/result}]
    (todos/delete-todo db id)
    [::response/no-content]))