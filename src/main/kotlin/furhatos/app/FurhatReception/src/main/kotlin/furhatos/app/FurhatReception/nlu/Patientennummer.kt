

import furhatos.nlu.Intent
//Erklärung: Vergleiche Klasse "nlu.Ja"

class Patientennummer : Intent() {
    //var name: String = ""
    //var vorname: String = ""
    override fun getExamples(lang: furhatos.util.Language): List<String> {
        return listOf("Patientennummer", "Patient", "nummer", "patient", "number", "ich habe eine Patientennummer",
            "patient number", "patient", "nummer", "patient", "number", "I have a patient number",
            "hasta numarası", "hasta", "nummer", "hasta", "numara", "Hasta numaram var",
            "număr de pacient", "pacient", "pacient", "număr","numa", "am un număr de pacient")
    }
    /*fun getFullName(): String =
        "$vorname $name"
*/
}