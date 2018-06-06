(def indentity
  (fn [rf]
    (fn ([] (rf))
        ([result] (rf result))
        ([result input]
         ; Sends the input as it is to the downstream reducer.
         (rf result input)))))
