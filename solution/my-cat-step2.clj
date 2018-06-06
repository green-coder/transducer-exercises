; Step 2
(def my-cat
  (fn [rf]
    (fn ([] (rf))
        ([result] (rf result))
        ([result input]
         (reduce (fn [acc val]
                   (if (#{:fish :heat} val)
                     acc
                     (rf acc val)))
                 result input)))))
