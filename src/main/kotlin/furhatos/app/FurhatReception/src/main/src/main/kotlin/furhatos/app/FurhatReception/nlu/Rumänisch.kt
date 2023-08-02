import furhatos.nlu.Intent
//Erklärung: Vergleiche Klasse "nlu.Ja"

class Rumänisch : Intent() {
    //var name: String = ""
    //var vorname: String = ""
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf(
           "Română" , "în limba română vă rog", "vorbesc în limba română", "românesc, sunt românesc", "sunt român"
        )
    }

}