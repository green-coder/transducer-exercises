; Step 1
(defn a-d-d [n]
  (fn [rf]
    (let [state (volatile! [])]
      (fn ([] (rf))
          ([result]
           (let [buffer @state]
             (-> (if (seq buffer)
                   (reduce rf result buffer)
                   result)
                 (rf))))
          ([result input]
           (let [buffer (conj @state input)]
             (if (>= (count buffer) n)
               (do
                 (vreset! state [])
                 (reduce rf result buffer))
               (do
                 (vreset! state buffer)
                 result))))))))
