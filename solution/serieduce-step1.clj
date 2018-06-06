; Step 1
(defn serieduce [func init-val]
  (fn [rf]
    (let [state (volatile! init-val)]
      (fn ([] (rf))
          ([result] (rf result))
          ([result input]
           (let [acc @state
                 new-acc (func acc input)]
             (vreset! state new-acc)
             (rf result new-acc)))))))
