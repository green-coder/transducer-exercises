; Step 2
(defn serieduce [func init-val]
  (fn [rf]
    (let [state (volatile! init-val)]
      (fn ([] (rf))
          ([result] (rf result))
          ([result input]
           (let [acc @state
                 new-acc (func acc input)]
             (vreset! state new-acc)
             ;; Supports early termination in reducer func
             (if (reduced? new-acc)
               (->> (unreduced new-acc)
                    (rf result)
                    ensure-reduced)
               (rf result new-acc))))))))
