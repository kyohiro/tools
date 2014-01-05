package source

import scala.collection.mutable
import org.springframework.beans.BeanWrapper
import org.springframework.beans.BeanWrapperImpl

case class ReflectItem(item: Any) {
  val wrapped = new BeanWrapperImpl(item) 
  
  def get(field: String) = wrapped.getPropertyValue(field)
  def set(field: String, value: Any) = wrapped.setPropertyValue(field, value)
}

case class Condition(field: String, op: String, value: Any) {
  def matchFor(sth: Any): Boolean = {
    val item = ReflectItem(sth)
    op match {
      case "=" => item.get(field) == value
      case _ => ???
    }
  }
}

case class Criteria(expression: String) {
  lazy val parsed = SimpleParser(expression).parse
  lazy val identifier = parsed.get._1
  lazy val conditions = parsed.get._2.map(x => Condition(x._1, "=", x._2))
}

trait Resource {
  val identifier: String
  val resource: List[Any]
  
  def read(criteria: Criteria): Option[Any] = {
    if (criteria.conditions.isEmpty) Some(resource)
    else Some(resource.filter(res => criteria.conditions.forall(c => c.matchFor(res))))
  }
}

trait SomeSource {
  
  def read(criteria: Criteria): Option[Any]
  
  def addRes(res: Resource): Unit 
  
  def getRes(identifier: String): Option[Resource]
}

trait SyncSource extends SomeSource {
  val otherSource: SomeSource
  
  def sync(res: Resource) = otherSource.addRes(res)
}

trait DBSource extends SomeSource {
  def read(criteria: Criteria): Option[Any] = ???
}

class CachedSource extends SomeSource {
  
  val resources = mutable.HashMap.empty[String, Resource]
  
  def read(criteria: Criteria): Option[Any] = resources.get(criteria.identifier) match {
    case Some(res) => res.read(criteria)
    case None => None
  }
    
  def addRes(res: Resource) = resources.put(res.identifier, res)
  
  def getRes(identifier: String) = resources.get(identifier)
}

class SourceManager {
  var sources = List.empty[SomeSource] 
  
  //sources should be registered in the order of priority
  def registerSources(src: List[SomeSource]) = sources ++= src
  
  def read(criteria: Criteria) = readSources(criteria, sources) 
    
  private[this] def readSources(criteria: Criteria, sources: List[SomeSource]): Option[Any] = {
    if (sources.isEmpty) None
    else sources.head.read(criteria) match {
      case None => readSources(criteria, sources.tail)
      case any => any
    }
  }
}