(defn debug
  ; Default parameters stuffs.
  ([] (debug 0))
  ([indent] (debug indent ">" "<"))
  ([indent in out]
   (let [spaces (apply str (repeat indent \space))]
     (debug (str spaces in)
            (str spaces out))))
  ; The transducer code starts here.
  ([in out]
   (fn [rf]
     (fn ([] (rf))
         ([result] (rf result))
         ([result input]
          (print in input \return)
          (let [ret (rf result input)]
            (print out ret \return)
            ret))))))
