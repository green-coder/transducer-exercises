(into []
      (comp cat
            (remove #(#{:fish :heat} %))
            (take-while #(not= :sleep %)))
      cat-data)
