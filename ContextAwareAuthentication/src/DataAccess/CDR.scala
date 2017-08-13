package DataAccess

class CDR {
  private var phoneNumber = "";
  private var time = "";
  private var direction = "";
  private var duration = "";

  def setPhoneNumber(phoneNumber: String) =
    {
      this.phoneNumber = phoneNumber
    }

  def getPhoneNumber(): String =
    {
      return this.phoneNumber
    }

  def setTime(time: String) =
    {
      this.time = time
    }

  def getTime(): String =
    {
      return this.time
    }

  def setDuration(duration: String) =
    {
      this.duration = duration
    }

  def getDuration(): String =
    {
      return this.duration
    }

  def setDirection(direction: String) =
    {
      this.direction = direction
    }

  def getDirection(): String =
    {
      return this.direction
    }
}