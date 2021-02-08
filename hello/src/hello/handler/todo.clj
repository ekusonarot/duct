(ns hello.handler.todo
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [integrant.core :as ig]))
            
(defmethod ig/init-key :hello.handler/todo [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok {:todo "data"}]))