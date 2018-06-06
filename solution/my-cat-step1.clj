; Step 1
(def my-cat
  (fn [rf]
    (fn ([] (rf))
        ([result] (rf result))
        ([result input]
         (reduce rf result input)))))
