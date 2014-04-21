(ns mynewapp.core
  (:require [seesaw.core :as s]
            [me.raynes.fs :as fs])
  (:use clojure.xml
        clojure.java.io) 
  (:gen-class))

(def name-input (s/text :columns 13))

(def hosts-check (s/checkbox :selected? false))

(defn make-prj-dir [name]
  (fs/mkdir (str "E:\\sites\\" name)))

(defn make-vhost [name]
  (let [file "E:\\xampp\\apache\\conf\\extra\\httpd-vhosts.conf"]
    (spit file 
          (str "<VirtualHost *:80>\n"
							 "    ServerAdmin webmaster@dummy-host2.example.com\"\n" 
							 "	  DocumentRoot \"E:/sites/" name "\"\n"
							 "	  ServerName " name ".local\n"
							 "	  ErrorLog \"logs/" name ".local.log\"\n"
							 "	  CustomLog \"logs/\" name \".local.log\" common\n"
							 "</VirtualHost>\n\n") 
          :append true)))

(defn make-host [name]
  (let [file "C:\\Windows\\System32\\drivers\\etc\\hosts"]
    (spit file (str "\n127.0.0.1    " name ".local") :append true)))

(defn generate [_]
  (let [name (s/text name-input)]
  (if (empty? name)
    (s/alert "Please write a project name!")
    (do
      (make-prj-dir name)
      (make-vhost name)
      (if (s/config hosts-check :selected?)
        (make-host name))
      (s/alert "Task completed!")))))

(def main-window (s/frame :title "Make new App",
                 :content (s/flow-panel
                            :align :left
                            :hgap 20
                            :vgap 20
                            :items ["Project Name: "
                                    name-input
                                    "With hosts (must run as admin)"
                                    hosts-check
                                    (s/button :text "Generate"
                                              :listen [:action generate])]),
                 :on-close :exit
                 :size [300 :by 180]))

(defn- center! [frame]
  (.setLocationRelativeTo frame nil)
  frame)

(defn -main [& args]
  (s/invoke-later
    (-> main-window
        center!
        s/show!)))