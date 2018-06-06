; From https://github.com/clojure/clojure/blob/clojure-1.9.0/src/clj/clojure/core.clj#L7532
(defn preserving-reduced [rf]
  (fn [a b]
    (let [ret (rf a b)]
     (if (reduced? ret)
       (reduced ret)
       ret))))

; Step 2
(defn beg [n]
  (fn [rf]
    (let [rrf (preserving-reduced rf)]
      (fn ([] (rf))
          ([result] (rf result))
          ([result input]
           (reduce rrf result (repeat n input)))))))
