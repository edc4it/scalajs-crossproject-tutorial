package demo.be

import akka.http.scaladsl.model.headers.`Access-Control-Allow-Origin`
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import demo.shared.{JsonSupport, Series}

trait SeriesService extends JsonSupport with PlayJsonSupport {
    lazy val seriesApi =
        path("api" / "series") {
            respondWithHeaders(`Access-Control-Allow-Origin`.*) {
                get {
                    complete(Seq(Series("8219","Sense8"), Series("9214","Better Call Saul")))
                }
            }
        }


}
