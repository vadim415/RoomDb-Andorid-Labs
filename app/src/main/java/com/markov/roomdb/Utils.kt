package com.markov.roomdb

import android.text.TextUtils

fun validateInputPost(
    title: String,
    content: String,
    category: String,
    image: String
): Boolean {
    return !(TextUtils.isEmpty(title)
            && TextUtils.isEmpty(content)
            && TextUtils.isEmpty(category)
            && TextUtils.isEmpty(image)
            )
}