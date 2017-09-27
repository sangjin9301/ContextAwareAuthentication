import DataAccess.ParseData
import Rules.Rule01
import Rules.Rule02
import Rules.Rule03
import Rules.Rule06

object Test {
  
  def main(args: Array[String]): Unit = 
  {
    var parser = new ParseData
    var datas = parser.parseCDR("e26edaae-ebb2-4c14-8575-f2335fc857ae")
    var list = parser.parseLocation("C:\\Users\\SANGJIN-NAM\\Desktop\\location.json")
    RuleExecute.execute(datas)
    var rule6 = new Rule06
    rule6.getDayOfWeek(list)
    rule6.test

//    CSVmanager.writeLocation(list)
  }
  
}