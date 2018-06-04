package demo.fe

import demo.shared.{JsonSupport, Series}
import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.ext.Ajax
import play.api.libs.json._

import scala.concurrent.Future
import scala.util.{Failure, Success}

object SampleFrontendApp extends JsonSupport {

    def main(args: Array[String]): Unit = {
        println(".")
        dom.window.addEventListener("load", initApplication)
    }

    import scala.concurrent.ExecutionContext.Implicits.global

    def initApplication(e: Event) = {
        println("initialising application …")

        getData.onComplete {
            case Success(v) => v.foreach(s => displayTitle(s.title))
            case Failure(e) => println(s"oops: $e")
        }

    }

    def getData: Future[Seq[Series]] =
        Ajax.get("http://localhost:3000/api/series")
                .map(_.responseText)
                .map(Json.parse(_))
                .map(_.as[Seq[Series]])

    def displayTitle(title: String) =
        dom.document.getElementById("titles").insertAdjacentHTML("beforeend", s"<li>$title</li>")

}
 
