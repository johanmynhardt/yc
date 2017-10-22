# Brief

See [Brief](doc/brief.md)

## Infrastructure

* UI: ClojureScript
  * Rum
  * Routing: bidi, accountant
  * Fontawesome

* Backend: Clojure
  * Routing: bidi

**Note**: The `public/resources/index.html` has been replaced by an index handler serving
from the Clojure backend.

To see the project in action (interactive development environment) run:

    lein figwheel

A browser session will be launched at [localhost:3449](http://localhost:3449/).

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

