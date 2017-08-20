import DataAccess.ParseData
import Rules.Rule01

object Test {
  
  def main(args: Array[String]): Unit = {
    var parser = new ParseData
    var datas = parser.parseCDR("006328a3-90a5-4802-8b1a-7840a12313ef")
    var rule = new Rule01
    rule.setData(datas)
    rule.compareData
  }
  
}