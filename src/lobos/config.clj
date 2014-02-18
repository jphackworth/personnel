(ns lobos.config
  (:use lobos.connectivity)
  (:require [personnel.models.schema :as schema]))

(open-global schema/db-spec)