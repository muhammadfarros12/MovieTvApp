package com.farroos.movietvapp_submissionbajp.ui

import com.farroos.movietvapp_submissionbajp.data.source.local.entity.DataModel

interface DataCallback {
    fun onItemClicked(data: DataModel)
}