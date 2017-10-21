(ns yc-app.core
    (:require [rum.core :as rum]
              [accountant.core :as accountant]
              [bidi.bidi :as bidi]
              [secretary.core :as secretary :refer-macros [defroute]]
              [yc-app.navigation :as navigation]
              [yc-app.pages :as pages]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

;---- link up page and route
(defmulti page-contents identity)

(defmethod page-contents :home [] (pages/home))
(defmethod page-contents :default [] (pages/not-found))


(rum/defc menu-button []
  [:button.header-button {:onClick #(js/console.log "echo")}])

(rum/defc search-button []
  [:button {:onClick #(js/alert "Launch Search")} [:i.fa.fa-search.fa-lg]])

(rum/defc widget-yc []
  [:div.allcaps.bold "Yuppiechef"])

(rum/defc yc []
  [:div
   [:header#main-menu
    (menu-button)
    (widget-yc)
    [:div.inline-group
     [:div "Shop"]
     [:div "Registry"]
     [:div "Gifting"]
     [:div "Subscriptions"]
     [:div "Trade"]]
    [:div.flex]
    [:div "bar"]
    (search-button)
    [:div ""]]
   [:div#content-wrapper
    [:h1 (:text @app-state)]
    [:h3 "Edit this and watch it change!"]
    (for [x (range 0 15)]
      [:p "a generated parapgraph " x])]])


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (rum/mount (yc)
             (. js/document (getElementById "app")))
)

(defn ^:export init! []
  (accountant/configure-navigation!
   {:nav-handler (fn [path]
                   (let [match (bidi/match-route navigation/app-routes path)
                         current-page (:handler match)
                         route-params (:route-params match)]
                     (println "current-page: " current-page)
                     (comment  (session/put! :route {:current-page current-page
                                                     :route-params route-params}))))
    :path-exists? (fn [path]
                    (boolean (bidi/match-route navigation/app-routes path)))})
  (accountant/dispatch-current!)
  (on-js-reload))

(init!)
