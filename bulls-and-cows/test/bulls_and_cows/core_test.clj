(ns bulls-and-cows.core-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.core :as core]))

(deftest number->digits
  (are [digits number]
    (= digits (core/number->digits number))
    [\1 \2 \3] "123"
    nil nil))

(deftest bulls
  (are [result guess secret]
    (= result (core/bulls
                (core/number->digits guess)
                (core/number->digits secret)))
    3 "123" "123"
    1 "122" "252"
    0 "123" "456"))

(deftest cow?
  (are [result secret digit]
    (= result (core/cow? secret digit))
    true "123" \2
    true "123" \3
    false "123" 2
    false "123" "2"))

(deftest bulls-and-cows
  (are [result guess secret]
    (= result (core/bulls-and-cows
           (core/number->digits guess)
           (core/number->digits secret)))
    2 "122" "252"
    1 "452" "122"))

(deftest cows
  (are [result guess secret]
    (= result (core/cows
                (core/number->digits guess)
                (core/number->digits secret)))
    0 "123" "123"
    1 "122" "452"
    0 "452" "122"))

(deftest check-guess
  (are [result guess secret]
    (= result (core/check-guess
                (core/number->digits guess)
                (core/number->digits secret)))
    {:correct? false :bulls 0 :cows 0} "123" "456"
    {:correct? true} "123" "123"
    {:correct? false :bulls 1 :cows 2} "123" "213"))

(comment (deftest generate-secret
   (is (=
         (core/number->digits " 345 ")
         (core/generate-secret))))

  ; одна итерация игры
  (comment
    (core/game-step " 345 "))

  ; запуск игры
  ; критерий остановки -- пользователь угадал число
  (comment
    (core/run-game)))