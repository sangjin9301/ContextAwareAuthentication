import DataAccess.ParseData
import DataAccess.TS2Time
import Rules.Rule01

object Test {
  
  def main(args: Array[String]): Unit = {
    var parser = new ParseData
    var datas = parser.parseCDR("e5817b97-cb56-471e-a202-ee2b61a2bcd9")
    var rule = new Rule01
    rule.setData(datas)
  }
  
}