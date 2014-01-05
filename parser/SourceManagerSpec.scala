package test

import org.scalatest.FunSuite
import source.SimpleParser
import source.Resource
import source.Criteria
import source.CachedSource
import scala.beans.BeanProperty
import org.scalatest.matchers.ShouldMatchers

class SourceManagerSpec extends FunSuite
                        with ShouldMatchers {
  test("Parser") {
    val items = List(AlertItem("123", 1.5), AlertItem("123", 2))
    val res = AlertResource("alerts", items) 
    val p = Criteria("""from alerts where name = "123" and total = 1.5""")
    
    val cachedSrc = new CachedSource
    cachedSrc.addRes(res)
    
    val s = cachedSrc.read(p).get
    s match {
      case a: List[Any] => a.size should be (1)
      case _ => 
    }
  }
}

object AlertItem {
  def apply(n: String, t: Double) = new AlertItem(n, t)
}

class AlertItem(n: String, t: Double) {
  @BeanProperty
  var name = n
  @BeanProperty
  var total = t
  
  override def toString = s"$name, $total"
}

case class AlertResource(identifier: String, resource: List[Any]) extends Resource