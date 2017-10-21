(ns yc-app.pages
  (:require [yc-app.navigation :refer [route-for]]))


(defn home []
  [:div
   [:h2 "Home"]
   [:a {:href "/does-not-exist"} "huh?"]])

;;-------------------------------------------------------
(defn not-found []
  [:div
   [:h2 "404 - Not found"]
   [:p
    "No handler for route "
    [:code  "[" (.. js/window -location -pathname) "]"]
    " was found :("]
   [:a {:href (route-for :home)} "Home"]])
