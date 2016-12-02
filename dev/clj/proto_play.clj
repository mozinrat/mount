(ns proto-play
  (:require [mount.tools.graph :as mount]
            [proto-repl-charts.graph :as proto]))

(defn mount->proto [graph]
  (reduce (fn [g {:keys [name deps]}]
            (-> g
                (update :nodes conj name)
                (update :edges conj (-> deps (conj name) vec))))
          {}
          graph))

(->> (mount/states-with-deps)
     mount->proto
     (proto/graph "a proto graph of mount states"))
