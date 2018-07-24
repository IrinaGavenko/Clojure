(ns bulls-and-cows.core)

(defn number->digits
  "Перевод вводимого числа в посимвольный формат"
  [value]
  (seq value))

(defn bulls
  "Подсчет быков"
  [guess secret]
  (->>
    (map = guess secret)
    (filter true?)
    count))

(defn cow?
  "Проверка: является ли цифра коровой"
  [secret digit]
  (contains? (set secret) digit))

(defn bulls-and-cows
  "Подсчет суммарного числа быков и коров"
  [guess secret]
  (->>
    (map (partial cow? secret) guess)
    (filter true?)
    (count)))

(defn cows
  "Подсчет коров"
  [guess secret]
  (-
    (bulls-and-cows guess secret)
    (bulls guess secret)))

(defn check-guess
  "Обработка текущего состояния и получение результата"
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

(defn generate-secret
  "Генерирование числа secret"
  []
  (->>
    (repeatedly #(rand-int 10000))
    (filter (partial < 1000))
    (first)
    (str)
    (number->digits)))

(defn game-step
  "Одна итерация игры: чтение числа пользователя и
  вывод реузльтата в консоль"
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

(defn run-game
  "Запуск игры
  Критерий остановки: пользователь отгадал число"
  []
  (let [secret (generate-secret)]
    (while (game-step secret))))

