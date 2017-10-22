(ns yc-app.core
    (:require [rum.core :as rum]
              [accountant.core :as accountant]
              [bidi.bidi :as bidi]
              [yc-app.components :as ycc]
              [yc-app.navigation :as navigation]
              [yc-app.pages :as pages]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

;---- link up page and route
(defmulti page-contents identity)

(defmethod page-contents :home [] (pages/home))
(defmethod page-contents :default [] (pages/not-found))

(rum/defc app < rum/reactive []
  [:div
(ycc/side-menu)
   [:header#main-menu
    
    (ycc/menu-button)
    (ycc/widget-yc)
    [:div.inline-group
     [:div "Shop"]
     [:div "Registry"]
     [:div "Gifting"]
     [:div "Subscriptions"]
     [:div "Trade"]]
    [:div.flex]
    (comment  [:div.inline-group
               (ycc/help-button)])
    (ycc/shop-button)
    (ycc/search-button)
    [:div]]
   [:div#content-wrapper
    (page-contents (get-in (rum/react app-state) [:route :current-page]))]])



(defn on-js-reload []
  (rum/mount (app)
             (. js/document (getElementById "app")))
)

(defn ^:export init! []
  (accountant/configure-navigation!
   {:nav-handler (fn [path]
                   (let [match (bidi/match-route navigation/app-routes path)
                         current-page (:handler match)
                         route-params (:route-params match)]
                     (swap! app-state assoc :route {:current-page current-page
                                                    :route-params route-params})))
    :path-exists? (fn [path]
                    (boolean (bidi/match-route navigation/app-routes path)))})
  (accountant/dispatch-current!)
  (on-js-reload))

(init!)
