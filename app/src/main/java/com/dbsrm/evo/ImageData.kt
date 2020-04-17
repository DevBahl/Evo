package com.dbsrm.evo

class ImageData {
    var title: String? = null
        private set
    var description: String? = null
        private set
    var url: String? = null
        private set

    constructor() {}
    constructor(title: String?, description: String?, url: String?) {
        this.title = title
        this.description = description
        this.url = url
    }

}