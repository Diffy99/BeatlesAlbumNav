package com.msmith.bottomnavigation

class Song {



        //Strictly a data class

        private var songName: String = ""
        private var songDetail: String = ""


        constructor(name: String, detail: String) {
            songName = name
            songDetail = detail


        }

        public fun setName(name: String) {
            songName = name
        }

        public fun getName(): String {
            return songName;
        }

        public fun setDetail(detail: String) {
            songDetail = detail
        }

        public fun getDetail(): String {
            return songDetail
        }

    }
