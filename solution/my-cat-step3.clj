; Step 3
(def my-cat
  (fn [rf]
    (fn ([] (rf))
        ([result] (rf result))
        ([result input]
         (reduce (fn [acc val]
                   (cond
                     (#{:fish :heat} val) acc
                     (= :sleep val) (reduced (reduced acc))
                     :else (rf acc val)))
                 result input)))))
