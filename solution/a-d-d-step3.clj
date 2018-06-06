; Step 3 - partition-all
(defn a-d-d [n]
  (fn [rf]
    (let [state (volatile! [])]
      (fn ([] (rf))
          ([result]
           (let [buffer @state]
             (-> (if (seq buffer)
                   (rf result buffer)
                   result)
                 (rf))))
          ([result input]
           (let [buffer (conj @state input)]
             (if (>= (count buffer) n)
               (do
                 (vreset! state [])
                 (rf result buffer))
               (do
                 (vreset! state buffer)
                 result))))))))
