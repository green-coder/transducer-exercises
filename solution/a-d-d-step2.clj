; From https://github.com/clojure/clojure/blob/clojure-1.9.0/src/clj/clojure/core.clj#L7532
(defn preserving-reduced [rf]
  (fn [a b]
    (let [ret (rf a b)]
     (if (reduced? ret)
       (reduced ret)
       ret))))

; Step 2
(defn a-d-d [n]
  (fn [rf]
    (let [rrf (preserving-reduced rf)]
      (let [state (volatile! [])]
        (fn ([] (rf))
            ([result]
             (let [buffer @state]
               (-> (if (seq buffer)
                     (reduce rrf result buffer)
                     result)
                   (rf))))
            ([result input]
             (let [buffer (conj @state input)]
               (if (>= (count buffer) n)
                 (do
                   (vreset! state [])
                   (reduce rrf result buffer))
                 (do
                   (vreset! state buffer)
                   result)))))))))
