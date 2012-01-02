;- Copyright © 2008-2011 8th Light, Inc. All Rights Reserved.
;- Limelight and all included source files are distributed under terms of the MIT License.

(ns limelight.clojure.prop-building-spec
  (:use
    [speclj.core]
    [limelight.clojure.spec-helper]
    [limelight.clojure.core]
    [limelight.clojure.prop-building]
    [limelight.clojure.scene :only (new-scene)]
    [limelight.clojure.production :only (new-production)]))

(defn illuminate [scene]
  (.setProduction @(._peer scene) (._peer (new-production (limelight.model.FakeProduction. "Mock Production"))))
  (.illuminate @(._peer scene)))

(describe "prop-building"

  (before-all (boot-limelight))

  (it "builds with no props"
    (let [scene (build-props (new-scene {}) "" "props.clj")]
      (should= 0 (count (child-props scene)))))

  (it "with one prop"
    (let [scene (build-props (new-scene {}) "[:one]" "props.clj")]
      (should= 1 (count (child-props scene)))))

  (it "with two prop"
    (let [scene (build-props (new-scene {}) "[:one][:two]" "props.clj")]
      (should= 2 (count (child-props scene)))))

  (it "nested props"
    (let [scene (build-props (new-scene {}) "[:one [:two]]" "props.clj")]
      (should= 1 (count (child-props scene)))
      (should= 1 (count (child-props (first (child-props scene)))))))

  (it "with options and a child prop"
    (let [scene (build-props (new-scene {}) "[:one {:text \"Number ONE!\"} [:two]]" "props.clj")]
      (should= 1 (count (child-props scene)))
      (should= 1 (count (child-props (first (child-props scene)))))))

  (it "with two children"
    (let [scene (build-props (new-scene {}) "[:one {:text \"Number ONE!\"} [:two] [:three]]" "props.clj")]
      (should= 2 (count (child-props (first (child-props scene)))))))

  (it "with illumination"
    (let [scene (build-props (new-scene {}) "[:one {:text \"Number ONE!\"} [:two]]" "props.clj")]
      (illuminate scene)
      (should= "one" (.getName @(._peer (first (child-props scene)))))
      (should= "Number ONE!" (.getText @(._peer (first (child-props scene)))))))

  (it "with dynamic code"
    (let [scene (build-props (new-scene {}) "[:one (for [name [:two :three]] [name])]" "props.clj")]
      (should= 2 (count (child-props (first (child-props scene)))))))

  (it "adds the props in the right order"
    (let [scene (build-props (new-scene {}) "[:one][:two][:three]" "props.clj")]
      (illuminate scene)
      (should= "one" (prop-name (first (child-props scene))))
      (should= "two" (prop-name (second (child-props scene))))
      (should= "three" (prop-name (nth (child-props scene) 2)))))

  (it "allows events in options"
    (System/setProperty "foo.bar" "nothing")
    (let [scene (build-props (new-scene {}) "[:one {:id :one :on-focus-lost (fn [_] (System/setProperty \"foo.bar\" \"fizz-bang\"))}]", "prop.clj")
          _ (illuminate scene)
          prop (first (child-props scene))
          panel (.getPeer prop)]
      (.dispatch (limelight.ui.events.panel.FocusLostEvent.) panel)
      (should= "fizz-bang" (System/getProperty "foo.bar"))))
  )

(run-specs)
