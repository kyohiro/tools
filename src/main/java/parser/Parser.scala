package source

import scala.util.parsing.combinator.JavaTokenParsers

object SimpleParser {
  def apply(expression: String) = new SimpleParser(expression)
}
class SimpleParser(expression: String) extends JavaTokenParsers {
  
  def parse = parseAll(syntax, expression)
  
  def syntax: Parser[(String, Map[String, Any])]  = ("from" ~ resource ~ "where" ~ conditions ^^ { case "from" ~ resource ~ "where" ~ conditions => (resource, conditions)}) | 
    ("from" ~ resource ^^ {case "from" ~ resource => (resource, Map.empty[String, Any])})
  
  def resource: Parser[String] = ident ^^ {case ident => ident}
  
  def conditions: Parser[Map[String, Any]] = repsep(condition, "and") ^^ { Map() ++ _ }
  
  def condition: Parser[(String, Any)] = resource ~ "=" ~ dataType ^^ {case resource ~ "=" ~ dataType => (resource, dataType)}  
  
  def dataType: Parser[Any] = stringLiteral ^^ (_.replaceAll(""""""", "")) | floatingPointNumber ^^ (_.toDouble) | "null" ^^ (x => null) | "true" ^^ (x => true) | "false" ^^ (x => false)
}

object TestParser extends App {
  val p = SimpleParser("""from alerts where name = "123" and total = 1.5""")
  println(p.parse)
}
