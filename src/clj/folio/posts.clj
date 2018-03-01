(ns folio.posts)

(defrecord Post [author timestamp subject body])

(defn timestamp
  []
  (quot (System/currentTimeMillis) 1000))

(def test-db (atom {:posts [(->Post "Trip" (timestamp) "First Post" "foo bar baz lorum ipsum")]}))


(defn get-all
  []
  ;;todo MONGO query here
  (:posts @test-db))

(defn add
  [post]
  (swap! test-db #(update % :posts conj post)))
