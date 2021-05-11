package com.pharos.foryoutube.data.response

import android.graphics.pdf.PdfDocument


data class PlayList(
    var kind: String,
    var etag: String,
    var items: List<Items>,
    var pageInfo: PdfDocument.PageInfo
)

data class Thumbnails(
    var default: Default,
    var medium: Medium,
    var high: High
)

data class Snippet(
    var publishedAt: String,
    var channelId: String,
    var title: String,
    var description: String,
    var thumbnails: Thumbnails,
    var channelTitle: String,
    var tags: List<String>,
    var categoryId: String,
    var liveBroadcastContent: String,
    var localized: Localized
)

data class PageInfo(
    var totalResults: Int,
    var resultsPerPage: Int
)

data class Medium(
    var url: String,
    var width: Int,
    var height: Int
)

data class Localized(
    var title: String,
    var description: String
)

data class Items(
    var kind: String,
    var etag: String,
    var id: String,
    var snippet: Snippet,
    var contentDetails: ContentDetails
)

data class High(
    var url: String,
    var width: Int,
    var height: Int
)

data class Default(
    var url: String,
    var width: Int,
    var height: Int
)

data class ContentRating(val name: String = "")

data class ContentDetails(
    var duration: String,
    var dimension: String,
    var definition: String,
    var caption: String,
    var licensedContent: Boolean,
    var contentRating: ContentRating,
    var projection: String,
    var itemCount: Int

)

