(ns yc-app.navigation
  (:require [bidi.bidi :as bidi]))

(def app-routes
  ["/" [["" :home]
        [true :not-found]]])

(defn route-for [path & params]
  "Convenience function wrapping app-routes and bidi/path-for."
  (apply bidi/path-for (vec (cons app-routes (vec (cons path params))))))
