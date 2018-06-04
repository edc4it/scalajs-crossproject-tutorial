package demo.shared

import play.api.libs.json

import play.api.libs.json.Json


trait JsonSupport  {
    implicit val SeriesFormat = Json.format[Series]
}
