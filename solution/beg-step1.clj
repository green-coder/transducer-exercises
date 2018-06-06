; Step 1
(defn beg [n]
  (fn [rf]
    (fn ([] (rf))
        ([result] (rf result))
        ([result input]
         (reduce rf result (repeat n input))))))
