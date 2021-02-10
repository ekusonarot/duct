(ns crud.handler.todos
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [integrant.core :as ig]
            [crud.boundary.todos :as todos]))

(defmethod ig/init-key ::list [_ {:keys [db]}]
  (fn [{[_] :ataraxy/result}]
    (let [todos (todos/get-todos db)]
      [::response/ok todos])))
