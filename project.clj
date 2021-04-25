(defproject scramblies-demo "0.1.0-SNAPSHOT"
  :description "scramblies backend"
  :url ""
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [compojure "1.6.2"]
                 [http-kit "2.5.3"]
                 [ring/ring-defaults "0.3.2"]
                 [ring-cors/ring-cors "0.1.13"]
                 [org.clojure/data.json "2.0.2"]]
  :main ^:skip-aot scramblies-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
