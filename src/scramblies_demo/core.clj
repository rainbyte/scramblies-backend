(ns scramblies-demo.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.cors :refer [wrap-cors]]
;            [clojure.string :as str]
            [clojure.data.json :as json])
  (:gen-class))

(defn getparam [req pname] (get (:params req) pname))

;(defn scramble-ord? [str1 str2]
;  (cond
;    (empty? str2) true
;    (empty? str1) false
;    (= (first str1) (first str2)) (scramble-ord? (rest str1) (rest str2))
;    :else (scramble-ord? (rest str1) str2)))

;(defn scramble? [str1 str2]
;  (let [s1 (sort str1) s2 (sort str2)] (scramble-ord? s1 s2)))

(defn scramble? [str1 str2]
  (let [fs1 (frequencies str1)
        fs2 (frequencies str2)]
    (every? (fn [k] (>= (get fs1 k 0) (get fs2 k)))
            (keys fs2))))

; Simple Body Page
(defn simple-body-page [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World"})

; Request Example
(defn request-scramble [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [p (partial getparam req)]
                  (str (json/write-str (scramble? (p :str1) (p :str2))))))})

(defroutes app-routes
  (GET "/" [] simple-body-page)
  (GET "/scramble" [] request-scramble)
  (route/not-found "Error, page not found!"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (wrap-cors
       :access-control-allow-origin [#".*"]
       :access-control-allow-headers ["Content-Type"]
       :access-control-allow-methods [:get :put :post :delete :options])))

(defn -main
  "App entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run server with middleware
    (server/run-server #'app
    ; (wrap-defaults #'app-routes site-defaults)
     {:port port})
    ; Run without middleware
    ;(server/run-server #'app-app-routes {:port port}))
    (println (str "Running webserver at http://127.0.0.1:" port "/"))))
