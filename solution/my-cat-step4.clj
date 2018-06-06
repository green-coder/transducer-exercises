; Step 4
(def my-cat
  (fn [rf]
    (fn ([] (rf))
        ([result] (rf result))
        ([result input]
         (reduce (fn [acc val]
                   (cond
                     (#{:fish :heat} val) acc
                     (= :sleep val) (reduced (reduced acc))
                     :else
                     (let [ret (rf acc val)]
                       (if (reduced? ret)
                         (reduced ret)
                         ret))))
                 result input)))))
