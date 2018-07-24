(ns bulls-and-cows.core-test
  (:require [clojure.test :refer :all]
            [bulls-and-cows.core :refer :all]))

(deftest _number->digit)

(deftest _generate-secret
  (is (=
        (number->digits "345")
        (generate-secret)
        )))

(deftest _bulls-1
  (is (=
        3
        (bulls
          (number->digits "123")
          (number->digits "123")))))

(deftest _bulls-2
  (is (=
        1
        (bulls
          (number->digits "122")
          (number->digits "252")))))
(deftest _bulls-3
  (is (=
        0
        (bulls
          (number->digits "123")
          (number->digits "456")))))
(deftest _cow?-1
  (is (=
        true
        (cow? "123" \2))))
(deftest _cow?-2
  (is (=
        true
        (cow? "123" \3))))
(deftest _cow?-3
  (is (=
        false
        (cow? "123" 2))))
(deftest _cow?-4
  (is (=
        false
        (cow? "123" "2"))))
(deftest _bulls-and-cows-1
  (is (=
        2
        (bulls-and-cows
          (number->digits "122")
          (number->digits "252")))))
(deftest _bulls-and-cows-2
  (is (=
        1
        (bulls-and-cows
          (number->digits "452")
          (number->digits "122")))))
(deftest _cows-1
  (is (=
        0
        (cows
          (number->digits "123")
          (number->digits "123")))))
(deftest _cows-2
  (is (=
        1
        (cows
          (number->digits "122")
          (number->digits "452")))))
(deftest _cows-3
  (is (=
        0
        (cows
          (number->digits "452")
          (number->digits "122")))))
(deftest _check-guess-1
  (is (=
        {:correct? false :bulls 1 :cows 0}
        (check-guess
          (number->digits "123")
          (number->digits "426")))))
(deftest _check-guess-2
  (is (=
        {:correct? false :bulls 1 :cows 2}
        (check-guess
          (number->digits "123")
          (number->digits "213")))))
(deftest _check-guess-3
  (is (=
        {:correct? true}
        (check-guess
          (number->digits "123")
          (number->digits "123")))))

; одна итерация игры
(comment
  (game-step "345"))

; запуск игры
; критерий остановки -- пользователь угадал число
(comment
  (run-game))
