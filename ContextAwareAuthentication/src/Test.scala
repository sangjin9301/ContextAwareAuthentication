import DataAccess.ParseData
import Rules.Rule01

object Test {
  
  def main(args: Array[String]): Unit = {
    var parser = new ParseData
    var datas = parser.parseCDR("e26edaae-ebb2-4c14-8575-f2335fc857ae")
    var rule = new Rule01
    rule.setData(datas)
    rule.compareData
  }
  
}