(ns yc-app.core
    (:require [rum.core :as rum]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(rum/defc menu-button []
  [:button.header-button {:onClick #(js/console.log "echo")}])

(rum/defc widget-yc []
  [:div.allcaps.bold "Yuppiechef"])

(rum/defc yc []
  [:div
   [:header#main-menu
    (menu-button)
    (widget-yc)
    [:div.flex]
    [:div "bar"]
    [:div "echo"]
    [:div ""]]
   [:div#content-wrapper
    [:h1 (:text @app-state)]
    [:h3 "Edit this and watch it change!"]
    (for [x (range 0 15)]
      [:p "a generated parapgraph " x])]])

(rum/mount (yc)
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
