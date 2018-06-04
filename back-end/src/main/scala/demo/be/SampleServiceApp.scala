package demo.be

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object SampleServiceApp extends App with SeriesService {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit lazy val ex: ExecutionContext = system.dispatcher

    Http().bindAndHandle(seriesApi, "0.0.0.0", 3000) onComplete {
        case Success(b) => println(s"server is running ${b.localAddress} ")
        case Failure(e) => println(s"there was an error starting the server $e")
    }

}
