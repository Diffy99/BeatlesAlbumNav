package com.msmith.bottomnavigation

class Albums {
    //Strictly a data class

    private var albumName: String = ""
    private var albumDetail: String = ""
    private var albumPic: String = ""



    constructor(name: String, detail: String, pic: String) {
        albumName = name
        albumDetail = detail
        albumPic = pic

    }

    public fun setName(name: String) {
        albumName = name
    }

    public fun getName(): String {
        return albumName;
    }

    public fun setDetail(detail: String) {
        albumDetail = detail
    }

    public fun getDetail(): String {
        return albumDetail
    }

    public fun setPic(pic: String)
    {
        albumPic = pic
    }

    public fun getPic(): String
    {
        return albumPic
    }

}