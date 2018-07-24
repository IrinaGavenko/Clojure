(ns bulls-and-cows.core)

; перевод вводимого числа в посимвольный формат
(defn number->digits
  [value]
  (seq value))

; подсчёт быков
(defn bulls
  [guess secret]
  (->>
    (map = guess secret)
    (filter true?)
    count))

; проверка -- является ли цифра коровой
(defn cow?
  [secret digit]
  (contains? (set secret) digit))

; подсчет суммарного числа быков и коров
(defn bulls-and-cows
  [guess secret]
  (->>
    (map #(cow? secret %)
      guess)
    (filter true?)
    (count)))

(bulls-and-cows
  (number->digits "123")
  (number->digits "453"))

; подсчет коров
(defn cows
  [guess secret]
  (-
    (bulls-and-cows guess secret)
    (bulls guess secret)))

; обработка текущего состояния и получение результата
(defn check-guess
  [guess secret]
  (if (= guess secret)
    {:correct? true}
    {:correct? false
     :bulls (bulls guess secret)
     :cows (cows guess secret)}))

(comment
  (check-guess
   {:guess  (number->digits "123")
    :secret (number->digits "452")}))

; генерирование числа secret
(defn generate-secret
  []
  (number->digits "345"))

; одна итерация игры: чтение числа пользователя
; и вывод результата в консоль
(defn game-step
  [secret]
  (let [{:keys [correct? bulls cows]}
        (->>
          (read-line)
          (number->digits)
          (check-guess secret))]
    (if correct?
      (println "You win!")
      (println (format "bulls %s, cows %s" bulls cows)))
    (not correct?)))

; запуск игры
; критерий остановки: пользователь отгадал число
(defn run-game
  []
  (let [secret (generate-secret)]
    (while (game-step secret))))
