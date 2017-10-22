(ns yc-app.pages
  (:require [yc-app.navigation :refer [route-for]]
            [yc-app.components :as ycc]))

;; TODO: I18N, L10N etc. to make it more accessible.

(defn home []
  [:div
   (ycc/focus-widget "See why we love them")
   [:br]
   [:nav#suggestions
    [:ul
     [:li [:a "Shop by " [:b "Brand"]]]
     [:li [:a "Great " [:b "Gifts"]]]
     [:li [:a "Most " [:b "Popular"]]]
     [:li [:a "Items on " [:b "Sale"]]]]]
   
   [:div#check-list
    [:ul.no-list
     [:li [:span.fa.fa-check.fa-fw] [:b "11 Years"] " of Kitchen & Homeware"]
     [:li [:span.fa.fa-check.fa-fw] [:b "Free delivery"] " for orders over R300"]
     [:li [:span.fa.fa-check.fa-fw] [:b "New store"] " open in Willowbridge"]]]
   [:a {:href "/does-not-exist"} "/does-not-exist"]])

;;-------------------------------------------------------
(defn not-found []
  [:div
   [:h2 "404 - Not found"]
   [:p
    "No handler for route "
    [:code  "[" (.. js/window -location -pathname) "]"]
    " was found :("]
   [:a {:href (route-for :home)} "Home"]])
